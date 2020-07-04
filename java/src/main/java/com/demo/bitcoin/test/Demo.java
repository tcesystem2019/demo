package com.demo.bitcoin.test;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.bitcoinj.core.Address;
import org.bitcoinj.core.DumpedPrivateKey;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.MainNetParams;

import java.util.Date;
import java.util.Map;


public class Demo {

    public static void main(String[] args) throws Exception {
//        Map<String, String> map = createAddress();
//        // 请妥善保存私钥和地址，后续操作都需要
//        System.out.println("地址："+map.get("fromAddr"));
//        System.out.println("私钥："+map.get("privateKey"));
//        地址：1FLc1UYjWER9gSHwCsvaAi2TUZQhfiuAst
//        私钥：L4zteFp6S5wb7sQkNJyDcVwCUVQNRKq3FySPyGszWw1ppq9vsLHF

        // put请求
//        String[] putAdds = {"1FLc1UYjWER9gSHwCsvaAi2TUZQhfiuAst"}; // 刚刚生成的地址
//        String putMetadata = "01010101"; // 协议
//        String putData = "01010101"; //上传内容转16进制数据
//        String putTimestamp = new Date().getTime() + "";  // 时间戳
//        StringBuilder putSign = new StringBuilder();
//        putSign.append(putAdds[0]).append(putMetadata).append(putData).append(putTimestamp);
//        String putSignature = signMsg(Sha256.getSHA256(putSign.toString()), "L4zteFp6S5wb7sQkNJyDcVwCUVQNRKq3FySPyGszWw1ppq9vsLHF"); // 签名，把参数内容拼接，然后sha256， 再传入自己保存的私钥
//        JSONObject resultPutInfo = requestPut(putAdds, putMetadata, putData, putTimestamp, putSignature);
//        System.out.println(resultPutInfo.toJSONString());

        // update请求
//        String[] updateAdds = {""}; // 刚刚生成的地址
//        String updateMetadata = "01010101"; // 协议
//        String updateData = "01010101"; //上传内容转16进制数据
//        String updateDirveId = "01010101"; //上传内容转16进制数据
//        String updateTimestamp = new Date().getTime() + "";  // 时间戳
//        StringBuilder updateSign = new StringBuilder();
//        updateSign.append(updateAdds.toString()).append(updateMetadata).append(updateData).append(updateTimestamp);
//        String updateSignature = signMsg(Sha256.getSHA256(putSign.toString()), ""); // 签名，把参数内容拼接，然后sha256， 再传入自己保存的私钥
//        JSONObject resultUpdateInfo = requestUpdate(updateAdds, updateMetadata, updateData, updateDirveId, updateTimestamp, updateSignature);
//        System.out.println(resultUpdateInfo.toJSONString());


        // 查询余额
//        String balanceAdds = "1NaF3hFm6hrRiZrHBVxyB9S7rLzBy5fYNA";
//        String balanceTimestamp = new Date().getTime() + "";  // 时间戳
//        StringBuilder balanceSign = new StringBuilder();
//        balanceSign.append(balanceAdds).append(balanceTimestamp);
//        String balanceSignature = signMsg(Sha256.getSHA256(balanceSign.toString()), "Kwe84LJbK9F2HPDcYsN6kLnWXcUd2bP12DScHN575f3qyuDDXgjV");
//        JSONObject resultBalance = requestBalance(balanceAdds, balanceTimestamp, balanceSignature);
//        System.out.println(resultBalance.toJSONString());

        // 查询历史记录
//        String histroryAdds = "1NaF3hFm6hrRiZrHBVxyB9S7rLzBy5fYNA";
//        String histroryTimestamp = new Date().getTime() + "";  // 时间戳
//        Integer page = 1;
//        StringBuilder histrorySign = new StringBuilder();
//        histrorySign.append(histroryAdds).append(page).append(histroryTimestamp);
//        String histrorySignature = signMsg(Sha256.getSHA256(histrorySign.toString()), "Kwe84LJbK9F2HPDcYsN6kLnWXcUd2bP12DScHN575f3qyuDDXgjV");
//        JSONObject resultHistory = requestGetTxHistory(histroryAdds, page, histroryTimestamp, histrorySignature);
//        System.out.println(resultHistory.toJSONString());


        // 查询driveid列表
//        String driveListAdds = "1NaF3hFm6hrRiZrHBVxyB9S7rLzBy5fYNA";
//        String driveListTimestamp = new Date().getTime() + "";  // 时间戳
//        StringBuilder driveListSign = new StringBuilder();
//        driveListSign.append(driveListAdds).append(driveListTimestamp);
//        String driveListSignature = signMsg(Sha256.getSHA256(driveListSign.toString()), "Kwe84LJbK9F2HPDcYsN6kLnWXcUd2bP12DScHN575f3qyuDDXgjV");
//        JSONObject driveList = requestGetDriveId(driveListAdds, driveListTimestamp, driveListSignature);
//        System.out.println(driveList);

        // 查询driveid列表
//        String driveListAdds = "1NaF3hFm6hrRiZrHBVxyB9S7rLzBy5fYNA";
//        String driveListTimestamp = new Date().getTime() + "";  // 时间戳
//        StringBuilder driveListSign = new StringBuilder();
//        driveListSign.append(driveListAdds).append(driveListTimestamp);
//        String driveListSignature = signMsg(Sha256.getSHA256(driveListSign.toString()), "Kwe84LJbK9F2HPDcYsN6kLnWXcUd2bP12DScHN575f3qyuDDXgjV");
//        JSONObject driveList = requestGetDriveId(driveListAdds, driveListTimestamp, driveListSignature);
//        System.out.println(driveList);


        // 查询指定driveId下的数据
//        String driveIdAdds = "1NaF3hFm6hrRiZrHBVxyB9S7rLzBy5fYNA";
//        String getdrive_id = "";
//        String driveIdTimestamp = new Date().getTime() + "";  // 时间戳
//        StringBuilder driveIdSign = new StringBuilder();
//        driveIdSign.append(driveIdAdds).append(getdrive_id).append(driveIdTimestamp);
//        String driveIdSignature = signMsg(Sha256.getSHA256(driveIdSign.toString()), "Kwe84LJbK9F2HPDcYsN6kLnWXcUd2bP12DScHN575f3qyuDDXgjV");
//        JSONObject getDriveId = requestGetDriveid(driveIdAdds, getdrive_id, driveIdTimestamp, driveIdSignature);
//        System.out.println(getDriveId);

        // 查询指定updateId下的数据
//        String updateIdAdds = "1NaF3hFm6hrRiZrHBVxyB9S7rLzBy5fYNA";
//        String getupdate_id = "";
//        String updateIdTimestamp = new Date().getTime() + "";  // 时间戳
//        StringBuilder updateIdSign = new StringBuilder();
//        updateIdSign.append(updateIdAdds).append(getupdate_id).append(updateIdTimestamp);
//        String updateIdSignature = signMsg(Sha256.getSHA256(updateIdSign.toString()), "Kwe84LJbK9F2HPDcYsN6kLnWXcUd2bP12DScHN575f3qyuDDXgjV");
//        JSONObject getUpdateId = requestGetUpdateid(updateIdAdds, getupdate_id, updateIdTimestamp, updateIdSignature);
//        System.out.println(getUpdateId);

        // 终结driveId
//        String terminateAdds = "1NaF3hFm6hrRiZrHBVxyB9S7rLzBy5fYNA";
//        String terminateDrive_id = "";
//        String terminateTimestamp = new Date().getTime() + "";  // 时间戳
//        StringBuilder terminateSign = new StringBuilder();
//        terminateSign.append(terminateAdds).append(terminateDrive_id).append(terminateTimestamp);
//        String terminateSignature = signMsg(Sha256.getSHA256(terminateSign.toString()), "Kwe84LJbK9F2HPDcYsN6kLnWXcUd2bP12DScHN575f3qyuDDXgjV");
//        JSONObject terminateDriveId = terminateDriveId(terminateAdds, terminateDrive_id, terminateTimestamp, terminateSignature);
//        System.out.println(terminateDriveId);

    }

    /**
     * 创建地址，私钥
     */
    public static Map<String, String> createAddress() {

        Map map = new HashedMap();
        NetworkParameters params = MainNetParams.get();
        ECKey key = new ECKey();
        String fromAddr = new Address(params, key.getPubKeyHash()).toBase58();
        String privateKey = key.getPrivateKeyEncoded(params).toBase58();
        map.put("fromAddr", fromAddr);
        map.put("privateKey", privateKey);
        return map;

    }

    public static JSONObject requestPut(String[] adds, String metadata, String data, String timestamp, String signature) throws Exception {

        JSONObject param = new JSONObject();
        param.put("addr", adds);
        param.put("metadata", metadata);
        param.put("data", data);
        param.put("timestamp", timestamp);
        param.put("signature", signature);
        JSONObject result = (JSONObject) JSONObject.parse(HttpUtil.doPost("http://freedrive.cash:8880/api/put", param.toJSONString()));
        return result;

    }

    public static JSONObject requestUpdate(String[] adds, String metadata, String data, String drive_id, String timestamp, String signature) throws Exception {

        JSONObject param = new JSONObject();
        param.put("addr", adds);
        param.put("metadata", metadata);
        param.put("data", data);
        param.put("drive_id", drive_id);
        param.put("timestamp", timestamp);
        param.put("signature", signature);
        JSONObject result = (JSONObject) JSONObject.parse(HttpUtil.doPost("http://freedrive.cash:8880/api/update", param.toJSONString()));
        return result;

    }

    public static JSONObject requestGetDriveid(String adds, String drive_id, String timestamp, String signature) throws Exception {

        JSONObject param = new JSONObject();
        param.put("addr", adds);
        param.put("drive_id", drive_id);
        param.put("timestamp", timestamp);
        param.put("signature", signature);
        JSONObject result = (JSONObject) JSONObject.parse(HttpUtil.doPost("http://freedrive.cash:8880/api/get", param.toJSONString()));
        return result;

    }

    public static JSONObject requestGetUpdateid(String adds, String update_id, String timestamp, String signature) throws Exception {

        JSONObject param = new JSONObject();
        param.put("addr", adds);
        param.put("update_id", update_id);
        param.put("timestamp", timestamp);
        param.put("signature", signature);
        JSONObject result = (JSONObject) JSONObject.parse(HttpUtil.doPost("http://freedrive.cash:8880/api/get", param.toJSONString()));
        return result;

    }

    public static JSONObject requestGetDriveId(String adds, String timestamp, String signature) throws Exception {

        JSONObject param = new JSONObject();
        param.put("addr", adds);
        param.put("timestamp", timestamp);
        param.put("signature", signature);
        JSONObject result = (JSONObject) JSONObject.parse(HttpUtil.doPost("http://freedrive.cash:8880/api/list_drive_id", param.toJSONString()));
        return result;

    }

    public static JSONObject requestBalance(String adds, String timestamp, String signature) throws Exception {

        JSONObject param = new JSONObject();
        param.put("addr", adds);
        param.put("timestamp", timestamp);
        param.put("signature", signature);
        JSONObject result = (JSONObject) JSONObject.parse(HttpUtil.doPost("http://freedrive.cash:8880/api/get_balance", param.toJSONString()));
        return result;

    }

    public static JSONObject requestGetTxHistory(String adds, Integer page, String timestamp, String signature) throws Exception {

        JSONObject param = new JSONObject();
        param.put("addr", adds);
        param.put("page", page);
        param.put("timestamp", timestamp);
        param.put("signature", signature);
        JSONObject result = (JSONObject) JSONObject.parse(HttpUtil.doPost("http://freedrive.cash:8880/api/get_tx_history", param.toJSONString()));
        return result;

    }

    public static JSONObject terminateDriveId(String adds, String drive_id, String timestamp, String signature) throws Exception {

        JSONObject param = new JSONObject();
        param.put("addr", adds);
        param.put("drive_id", drive_id);
        param.put("timestamp", timestamp);
        param.put("signature", signature);
        JSONObject result = (JSONObject) JSONObject.parse(HttpUtil.doPost("http://freedrive.cash:8880/api/terminate_drive_id", param.toJSONString()));
        return result;

    }


    public static String signMsg(String msg, String privateKey) {
        NetworkParameters networkParameters = MainNetParams.get();
        DumpedPrivateKey priKey = DumpedPrivateKey.fromBase58(networkParameters, privateKey);
        ECKey ecKey = priKey.getKey();
        return ecKey.signMessage(msg);
    }




}
