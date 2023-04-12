export default class AuthorizationService {
    static async authorize(login, password) {
        const url = "http://localhost:8080/api/login";
        const response = await fetch(url, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            credentials: "include",
            body: JSON.stringify({"login": login.toString(), "password": password.toString()})
        });
        return response;
    }
}