import axios from "axios";

const api = axios.create({
    baseURL: `http://localhost:8080/vote/v1`,
});

export default api;