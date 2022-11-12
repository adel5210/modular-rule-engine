import http from "../http-common";

class DataService{
    addRule(data){
        return http.post("/customers", data, {
            headers: authHeader()
        });
    }
}

export default new DataService();