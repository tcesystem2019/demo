#include <iostream>
#include <string>
#include <ctime>

#include <curl/curl.h>
#include "json.hpp"
#include "picosha2.h"

using namespace std;
using json = nlohmann::json;

const string dapp_addr = "13p2BRMRaXTEkHYhAM1X373p96yaceGQRn";
const string user_addr = "1DJsHi1WXtUpEEiAJxAV4EH5p4skBmh83B";

const string api_get_balance = "/api/get_balance";
const string api_put = "/api/put";
const string api_get = "/api/get";
const string api_update = "/api/update";
const string api_list_drive = "/api/list_drive_id";
const string api_list_all = "/api/list_all_drive_id";
const string api_get_history = "/api/get_tx_history";
const string api_set_auth = "/api/set_auth";


string hash256(const string str) {
  string result;
  picosha2::hash256_hex_string(str, result);
  return result;
}

static size_t ReplyCallback(void *ptr, size_t size, size_t nmemb, void *stream)
{
  std::string *str = (std::string*)stream;
  (*str).append((char*)ptr, size*nmemb);
  return size * nmemb;
}

bool rpc(const string data, string &response)
{
  string content = "content-type:text/plain;";
  string url = "127.0.0.1:8332";
  string auth = "dev:a";
  CURL *curl = curl_easy_init();
  struct curl_slist *headers = NULL;
  CURLcode res;
  string error_str;
  if (curl)
  {
    headers = curl_slist_append(headers, content.c_str());
    curl_easy_setopt(curl, CURLOPT_HTTPHEADER, headers);
    curl_easy_setopt(curl, CURLOPT_URL, url.c_str());
    curl_easy_setopt(curl, CURLOPT_POSTFIELDSIZE, (long)data.size());
    curl_easy_setopt(curl, CURLOPT_POSTFIELDS, data.c_str());

    curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, ReplyCallback);
    curl_easy_setopt(curl, CURLOPT_WRITEDATA, (void *)&response);
    curl_easy_setopt(curl, CURLOPT_USERPWD, auth.c_str());
    curl_easy_setopt(curl, CURLOPT_HTTPAUTH, CURLAUTH_ANY);

    curl_easy_setopt(curl, CURLOPT_USE_SSL, CURLUSESSL_TRY);
    curl_easy_setopt(curl, CURLOPT_CONNECTTIMEOUT, 30);
    curl_easy_setopt(curl, CURLOPT_TIMEOUT, 30);
    res = curl_easy_perform(curl);
  }
  curl_easy_cleanup(curl);
    
  if (res != CURLE_OK)
  {
    error_str = curl_easy_strerror(res);
    return false;
  }
  return true;
}

bool postJson(const string api, const string params, string &response)
{
  string content = "content-type:application/json;";
  string url = "http://freedrive.cash:8880" + api;
  CURL *curl = curl_easy_init();
  struct curl_slist *headers = NULL;
  CURLcode res;
  string error_str;
  if (curl)
  {
    headers = curl_slist_append(headers, content.c_str());
    curl_easy_setopt(curl, CURLOPT_HTTPHEADER, headers);
    curl_easy_setopt(curl, CURLOPT_URL, url.c_str());
    curl_easy_setopt(curl, CURLOPT_POSTFIELDSIZE, (long)params.size());
    curl_easy_setopt(curl, CURLOPT_POSTFIELDS, params.c_str());

    curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, ReplyCallback);
    curl_easy_setopt(curl, CURLOPT_WRITEDATA, (void *)&response);
    curl_easy_setopt(curl, CURLOPT_HTTPAUTH, CURLAUTH_ANY);

    curl_easy_setopt(curl, CURLOPT_USE_SSL, CURLUSESSL_TRY);
    curl_easy_setopt(curl, CURLOPT_CONNECTTIMEOUT, 30);
    curl_easy_setopt(curl, CURLOPT_TIMEOUT, 30);
    res = curl_easy_perform(curl);
  }
  curl_easy_cleanup(curl);
    
  if (res != CURLE_OK)
  {
    error_str = curl_easy_strerror(res);
    return false;
  }
  return true;
}

string signMessage(const string address, const string msg)
{
  string hash = hash256(msg);

  json json_data;
  json_data["jsonrpc"] = "1.0";
  json_data["id"] = "curltest";
  json_data["method"] = "signmessage";

  json json_params = json::array();
  json_params.push_back(address);
  json_params.push_back(hash);
  json_data["params"] = json_params;
  
  string response;
  rpc(json_data.dump(), response);

  json json_response = json::parse(response);
  string result = json_response["result"].get<string>();
  return result;
}

string getTimeStamp() {
    return to_string(std::time(0));
}

void getBalance() {
  string timestamp = getTimeStamp();
  string msg = api_get_balance + dapp_addr + timestamp;  
  string sign = signMessage(dapp_addr, msg);

  json params;
  params["dapp_addr"] = dapp_addr;
  params["timestamp"] = timestamp;
  params["dapp_signature"] = sign;
  string response;
  postJson(api_get_balance, params.dump(), response);
  std::cout << response << std::endl;
}

void put() {
  string timestamp = getTimeStamp();
  string metadata = "01";
  string data = "01";
  string msg = api_put + user_addr + dapp_addr + metadata + data + timestamp;  
  string sign = signMessage(user_addr, msg);

  json params;
  params["user_addr"] = user_addr;
  params["dapp_addr"] = dapp_addr;
  params["metadata"] = metadata;
  params["data"] = data;
  params["timestamp"] = timestamp;
  params["user_signature"] = sign;
  string response;
  postJson(api_put, params.dump(), response);
  std::cout << response << std::endl;
}

void update(const string drive_id) {
  string timestamp = getTimeStamp();
  string metadata = "02";
  string data = "02";
  string msg = api_update + user_addr + dapp_addr + metadata + data + drive_id + timestamp;  
  string sign = signMessage(user_addr, msg);

  json params;
  params["user_addr"] = user_addr;
  params["dapp_addr"] = dapp_addr;
  params["metadata"] = metadata;
  params["data"] = data;
  params["drive_id"] = drive_id;
  params["timestamp"] = timestamp;
  params["user_signature"] = sign;
  string response;
  postJson(api_update, params.dump(), response);
  std::cout << response << std::endl;
}

void get(const string drive_id) {
  string timestamp = getTimeStamp();
  string msg = api_get + dapp_addr + drive_id + timestamp;  
  string sign = signMessage(dapp_addr, msg);

  json params;
  params["dapp_addr"] = dapp_addr;
  params["drive_id"] = drive_id;
  params["timestamp"] = timestamp;
  params["dapp_signature"] = sign;
  string response;
  postJson(api_get, params.dump(), response);
  std::cout << response << std::endl;
}

void listDriveID() {
  string timestamp = getTimeStamp();
  string msg = api_list_drive + user_addr + dapp_addr + timestamp;  
  string sign = signMessage(dapp_addr, msg);

  json params;
  params["user_addr"] = user_addr;
  params["dapp_addr"] = dapp_addr;
  params["timestamp"] = timestamp;
  params["dapp_signature"] = sign;
  string response;
  postJson(api_list_drive, params.dump(), response);
  std::cout << response << std::endl;
}

void listAllDrive() {
  string timestamp = getTimeStamp();
  string protocol = "FOCP1V5";
  int page = 1;
  int detail = 0;
  string msg = api_list_all + protocol + dapp_addr + to_string(page) + to_string(detail) + timestamp;  
  string sign = signMessage(dapp_addr, msg);

  json params;
  params["protocol"] = protocol;
  params["dapp_addr"] = dapp_addr;
  params["page"] = page;
  params["detail"] = detail;
  params["timestamp"] = timestamp;
  params["dapp_signature"] = sign;
  string response;
  postJson(api_list_all, params.dump(), response);
  std::cout << response << std::endl;
}

void getHistory() {
  string timestamp = getTimeStamp();
  int page = 7;
  string msg = api_get_history + dapp_addr + to_string(page) + timestamp;  
  string sign = signMessage(dapp_addr, msg);

  json params;
  params["dapp_addr"] = dapp_addr;
  params["page"] = page;
  params["timestamp"] = timestamp;
  params["dapp_signature"] = sign;
  string response;
  postJson(api_get_history, params.dump(), response);
  std::cout << response << std::endl;
}

void setAuth(const string drive_id) {
  string timestamp = getTimeStamp();
  string admin = "[0]";
  string member = "[0]";
  string msg = api_set_auth + user_addr + dapp_addr + drive_id + "00" + timestamp;  
  string sign = signMessage(user_addr, msg);

  json params;
  params["user_addr"] = user_addr;
  params["dapp_addr"] = dapp_addr;
  params["drive_id"] = drive_id;
  params["admin"] = admin;
  params["member"] = member;
  params["timestamp"] = timestamp;
  params["user_signature"] = sign;
  string response;
  postJson(api_set_auth, params.dump(), response);
  std::cout << response << std::endl;
}

int main(int argc, char *argv[])
{
  getBalance();
  getHistory();

  put();
  listDriveID();
  listAllDrive();

  string drive_id = "5c4d2b06bdb186f5050473420e833bc5db25bb5171e77852047c55f575933b96";
  update("drive_id");
  get(drive_id);
  setAuth(drive_id);

  return 0;
}



