/* eslint-disable no-undef */

import axios from 'axios'
import Cookies from 'js-cookie'

axios.defaults.withCredentials = true;

const sendHttpGet = async (url) => {
    return new axios.get(url, {}).then(response => {
        if (response.data.code === 200) {
            return response.data.data;
        } else ElMessage({ message: response.data.message, type: 'error', plain: true});
    }).catch(async (error) => {
        throw new Error(error);
    });
};

const sendGetRequest = async (url) => {
    return new axios.get(import.meta.env.VITE_SERVER_URL + url, {
        headers: {
            "uid": localStorage.getItem('aide_uid'),
            "token": localStorage.getItem('aide_token')
        }
    }).then(response => {
        return response.data;
    }).catch(async () => {
        ElMessage({ message: "请求失败", type: 'error', plain: true});
    });  
};

const sendPostRequest = async (url, data) => {
    return new axios.post(import.meta.env.VITE_SERVER_URL + url, data, {
        headers: {
            "uid": localStorage.getItem('aide_uid'),
            "token": localStorage.getItem('aide_token')
        }
    }).then(response => {
        return response.data;
    }).catch(async () => {
        ElMessage({ message: "请求失败", type: 'error', plain: true});
    });  
};

const sendLogoutRequest = async () => {
    return new axios.post(import.meta.env.VITE_SERVER_URL + "admin/user/logout", null, {
        headers: {
            "uid": localStorage.getItem('aide_uid'),
            "token": localStorage.getItem('aide_token')
        }
    }).then(response => {
        console.log(response)
        if (response.data.code === 200) {
            ElMessage({ message: response.data.message, type: 'success', plain: true});
            setTimeout(() => location.href = "/login", 1000);
        } else ElMessage({ message: response.data.message, type: 'error', plain: true});
        return response.data;
    }).catch(async () => {
        ElMessage({ message: "请求失败", type: 'error', plain: true});
    });
}

const validUserToken = async () => {
    return new axios.post(import.meta.env.VITE_SERVER_URL + "admin/user/token", null, {
        headers: {
            "uid": localStorage.getItem('aide_uid'),
            "token": localStorage.getItem('aide_token')
        }
    }).then(response => {
        if (response.data.code !== 200) {
            Cookies.remove("token");
            localStorage.removeItem('aide_login');
            location.href = "/login";
        }
        return response.data;
    }).catch(async () => {
        ElMessage({ message: "请求失败", type: 'error', plain: true});
    });
}

const sendLoginRequest = async (username, password) => {
    return new axios.post(import.meta.env.VITE_SERVER_URL + "admin/user/login", {
        username: username,
        password: password
    }).then(response => {
        if (response.data.code === 200) {
            localStorage.setItem('aide_login', 'true');
            localStorage.setItem('aide_uid', response.data.data.uid);
            localStorage.setItem('aide_token', response.data.data.token);
        }
        return response.data;
    }).catch(async () => {
        ElMessage({ message: "请求失败", type: 'error', plain: true});
    });
}

export default { sendHttpGet, sendGetRequest, sendPostRequest, sendLogoutRequest, sendLoginRequest, validUserToken };