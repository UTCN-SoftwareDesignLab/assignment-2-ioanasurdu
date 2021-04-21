import authHeader, { BASE_URL, HTTP } from "../http";

export default {
    allBooks() {
        return HTTP.get(BASE_URL + "/books", { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    create(item) {
        return HTTP.post(BASE_URL + "/books", item, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    edit(item) {
        return HTTP.patch(BASE_URL + "/books", item, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    delete(id) {
        return HTTP.delete(BASE_URL + "/books/" + id, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    sell(item) {
        return HTTP.patch(BASE_URL + "/books", item, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
};
