/* eslint-disable no-undef */

import axios from 'axios'

const sendHttpGet = async (url) => {
    return new axios.get(url).then(data => {
        if (data.data.code === 200) {
            return data.data.data;
        } else ElMessage({ message: data.data.message, type: 'error', plain: true});
    }).catch(async (error) => {
        throw new Error(error);
    });
};

export default { sendHttpGet };