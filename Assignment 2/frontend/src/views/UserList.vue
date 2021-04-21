<template>
    <v-card>
        <v-card-title>
            Users
            <v-spacer></v-spacer>
            <v-text-field
                    v-model="search"
                    append-icon="mdi-magnify"
                    label="Search"
                    single-line
                    hide-details
            ></v-text-field>
            <v-btn @click="createUser">Create User</v-btn>
            <v-btn @click="books">Books</v-btn>
            <v-btn @click="pdf">Pdf</v-btn>
            <v-btn @click="csv">Csv</v-btn>
        </v-card-title>
        <v-data-table
                :headers="headers"
                :items="users"
                :search="search"
                @click:row="editUser"
        ></v-data-table>
        <AdminDialog
                :opened="dialogVisible"
                :user="selectedUser"
                @refresh="refreshList"
        ></AdminDialog>
    </v-card>
</template>

<script>
    import api from "../api";
    import AdminDialog from "../components/AdminDialog";
    import router from "@/router";

    export default {
        name: "UserList",
        components: {AdminDialog},
        data() {
            return {
                users: [],
                search: "",
                headers: [
                    {
                        text: "Username",
                        align: "start",
                        sortable: false,
                        value: "name",
                    },
                    { text: "Email", value: "email" },
                    { text: "Roles", value: "roles" },
                ],
                dialogVisible: false,
                selectedUser: {},
            };
        },
        methods: {
            editUser(user) {
                this.selectedUser = user;
                this.dialogVisible = true;
            },
            createUser() {
                this.dialogVisible = true;
            },
            books(){
                router.push("/items")
            },
            pdf(){
                this.users.pdf()
            },
            csv(){
                this.users.csv()
            },
            async refreshList() {
                this.dialogVisible = false;
                this.selectedUser = {};
                this.users = await api.users.allUsers();
            },},
        async created() {
            this.users = await api.users.allUsers();
            this.refreshList()
        },
    };
</script>

<style scoped></style>
