import http from "../http-common";

class DataService{
    addRule(data){
        return http.post("/customers", data);
    }
}

export default new DataService();