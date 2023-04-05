export default class StudentService {
    static async addStudent(student) {
        const url = "http://localhost:8080/api/students";
        const response =  await fetch(url, {
            method: "POST",
            headers: {'Content-Type': 'application/json'},
            body: student
        });
    }
}