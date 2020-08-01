import datetime
import hashlib
import json

import requests
from bitcoin.main import ecdsa_sign, privkey_to_address, random_key, hex_to_b58check

'''
KzpviYSDqdPUMok3cJSp8AgmAi95JFrg4VPgtwGZTsD3WzvJBKuG 私钥
1PS7RfrC2fqqkBKrWDQbpqUaSx25AVySaY
'''

# dapp_private_key = hex_to_b58check(random_key())  # 创建随机私钥
dapp_private_key = 'KzpviYSDqdPUMok3cJSp8AgmAi95JFrg4VPgtwGZTsD3WzvJBKuG'
dapp_address = privkey_to_address(dapp_private_key)  # 根据私钥创建地址

# dapp_private_key = hex_to_b58check('cc3327aa3ac557b13649f78c2e5a8d2c114cb03d41a393f06421a81495eb8927')  # 根据hex还原私钥
# dapp_address = privkey_to_address(dapp_private_key)

print(dapp_private_key, dapp_address)
hosts = 'https://open.api.qingniao.cloud'


# hashs = hashlib.sha256(b'123456').hexdigest()
# print(hashs,dapp_private_key)
# print(ecdsa_sign(hashs,dapp_private_key))


# 获取余额
def get_balance():
    urls = '/api/get_balance'
    times = str(int(datetime.datetime.timestamp(datetime.datetime.now())))
    u_data = {
        "dapp_addr": dapp_address,
        "timestamp": times,
    }
    u_data['dapp_signature'] = sign(urls, u_data, dapp_private_key)
    re = requests.post(hosts + urls, json=u_data)
    return re.json()


# 上传数据
def put_data(metadata, main_data, drive_id=0):
    urls = '/api/put'
    times = str(int(datetime.datetime.timestamp(datetime.datetime.now()) * 1000))
    print(times)
    u_data = {
        "user_addr": dapp_address,
        "dapp_addr": dapp_address,
        "metadata": metadata.encode('utf8').hex(),
        "data": main_data.encode('utf8').hex(),
        "drive_id": drive_id,
        "timestamp": times
    }
    u_data['user_signature'] = sign(urls, u_data, dapp_private_key)
    re = requests.post(hosts + urls, json=u_data)
    return re.json()


# 更新数据
def update_data(metadata, data, drive_id):
    urls = '/api/put'
    times = str(int(datetime.datetime.timestamp(datetime.datetime.now()))*1000)
    u_data = {
        "user_addr": dapp_address,
        "dapp_addr": dapp_address,
        "metadata": metadata.encode('utf8').hex(),
        "data": data.encode('utf8').hex(),
        "drive_id": drive_id,
        "timestamp": times
    }
    u_data['user_signature'] = sign(urls, u_data, dapp_private_key)
    re = requests.post(hosts + urls, json=u_data)
    return re.json()


# 获取数据
def get_data(drive_id, prev=0, next=0):
    urls = '/api/get'
    times = str(int(datetime.datetime.timestamp(datetime.datetime.now())) * 1000)
    u_data = {
        "dapp_addr": dapp_address,
        "drive_id": drive_id,
        "prev": prev,
        "next": next,
        "timestamp": times
    }
    u_data['dapp_signature'] = sign(urls, u_data, dapp_private_key)
    re = requests.post(hosts + urls, json=u_data)
    return re.json()


# 获取数据列表
def get_list(addr):
    urls = '/api/list'
    times = str(int(datetime.datetime.timestamp(datetime.datetime.now())) * 1000)
    u_data = {
        "protocol": 0,  # 传"0"表示不指定某个协议查询
        "addr": dapp_address,  # 传"0"表示不指定某个地址查询
        "dapp_addr": dapp_address,
        "page": 1,  # 获取第几页
        "detail": 1,  # (1:true, 表示获取drive_id详情, 只返回第1条和最新1条数据;  0:false, 表示不返回driveid详情)
        "timestamp": times,
    }
    u_data['dapp_signature'] = sign(urls, u_data, dapp_private_key)
    re = requests.post(hosts + urls, json=u_data)
    return re.text


# 签名
def sign(url_name, param, sign_key):
    vs = url_name
    for k, v in param.items():
        vs += str(v)
    hashs = hashlib.sha256(vs.encode()).hexdigest()
    signature = ecdsa_sign(hashs, sign_key)
    return signature


print(put_data('372819378291', 'test','8eb426da7c140cfce22040b6e876320744f3d4909303be74937fcaa0315ce3c0'))
print(get_data('b4bf5206e6e10fbecf2367673347ffa14d1637eff41bdf848b7b7988770dcef5'))
print(update_data('test', '更新一条消息', 'b4bf5206e6e10fbecf2367673347ffa14d1637eff41bdf848b7b7988770dcef5'))
print(get_list(dapp_address))
