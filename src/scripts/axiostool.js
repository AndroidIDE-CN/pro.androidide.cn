
import axios from 'axios'

const sendHttpGet = async (url) => {
    return new axios.get(url).then(data => {
        return data;
    }).catch(async (error) => {
        throw new Error(error);
    });
};

export default { sendHttpGet };