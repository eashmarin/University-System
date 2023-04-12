export default class GroupService {
    static async getGroupList(groupId) {
        const url = "http://localhost:8080/api/groups/" + groupId + "/list";
        const response = await fetch(url, {credentials: "include"});
        return await response.json();
    }

    static async addGroup(group) {
        const url = "http://localhost:8080/api/groups/";
        const response = await fetch(url, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
            },
            credentials: "include",
            body: group
        });
        return response;
    }
}