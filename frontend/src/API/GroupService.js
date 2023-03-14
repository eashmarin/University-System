export default class GroupService {
    static async getGroup(groupId) {
        const url = "http://localhost:8080/api/groups/" + groupId + "/list";
        const response =  await fetch(url);
        return await response.json();
    }
}