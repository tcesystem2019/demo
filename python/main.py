import datetime
import hashlib
import requests
from bitcoin.main import ecdsa_sign, privkey_to_address, random_key, hex_to_b58check


dapp_private_key = hex_to_b58check(random_key())  # 创建私钥
user_private_key = hex_to_b58check(random_key())

dapp_address = privkey_to_address(dapp_private_key)  # 根据私钥创建地址
user_address = privkey_to_address(user_private_key)


hosts = 'http://freedrive.cash:8880'


# 获取余额
def get_balance():
    urls = '/api/get_balance'
    times = str(int(datetime.datetime.timestamp(datetime.datetime.now())))
    u_data = {
        "dapp_addr": dapp_address,
        "timestamp": times,
    }
    u_data['dapp_signature'] = signe(urls, u_data, dapp_private_key)
    re = requests.post(hosts + urls, json=u_data)
    return re.json()


# 上传数据
def put_data(metadata, main_data):
    urls = '/api/put'
    times = str(int(datetime.datetime.timestamp(datetime.datetime.now())))
    u_data = {
        "user_addr": dapp_address,
        "dapp_addr": dapp_address,
        "metadata": metadata.encode('utf8').hex(),
        "data": main_data.encode('utf8').hex(),
        "timestamp": times
    }
    u_data['user_signature'] = signe(urls, u_data, dapp_private_key)
    re = requests.post(hosts + urls, json=u_data)
    return re.json()


# 更新数据
def update_data(metadata, data, drive_id):
    urls = '/api/update'
    times = str(int(datetime.datetime.timestamp(datetime.datetime.now())))
    u_data = {
        "user_addr": dapp_address,
        "dapp_addr": dapp_address,
        "metadata": str(metadata).encode('utf8').hex(),
        "data": str(data).encode('utf8').hex(),
        "drive_id": drive_id,
        "timestamp": times
    }
    u_data['user_signature'] = signe(urls, u_data, dapp_private_key)
    re = requests.post(hosts + urls, json=u_data)
    return re.json()


# 获取数据
def get_data(drive_id):
    urls = '/api/get'
    times = str(int(datetime.datetime.timestamp(datetime.datetime.now())))
    u_data = {
        "dapp_addr": dapp_address,
        "drive_id": drive_id,
        "timestamp": times
    }
    u_data['dapp_signature'] = signe(urls, u_data, dapp_private_key)
    re = requests.post(hosts + urls, json=u_data)
    return re.json()


# 根据drive_id获取数据列表
def get_list_drive_id():
    urls = '/api/list_drive_id'
    times = str(int(datetime.datetime.timestamp(datetime.datetime.now())))
    u_data = {
        "user_addr": dapp_address,
        "dapp_addr": dapp_address,
        "timestamp": times
    }
    u_data['dapp_signature'] = signe(urls, u_data, dapp_private_key)
    re = requests.post(hosts + urls, json=u_data)
    return re.json()


# 签名
def signe(url_name, param, sign_key):
    vs = url_name
    for k, v in param.items():
        vs += str(v)
    hashs = hashlib.sha256(vs.encode()).hexdigest()
    signature = ecdsa_sign(hashs, sign_key)
    return signature


