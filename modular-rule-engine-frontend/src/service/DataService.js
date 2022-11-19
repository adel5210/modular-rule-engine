import http from "../http-common";

class DataService{
    getRuleGroups(pData){
        return http.get("/ruleSet", {params: pData});
    }
}

export default new DataService();