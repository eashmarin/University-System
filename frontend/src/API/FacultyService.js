export default class FacultyService {
    static async getFaculty(facultyId) {
        const url = "http://localhost:8080/api/faculties/" + facultyId;
        const response =  await fetch(url);
        const json = await response.json();
        return json;
    }
}