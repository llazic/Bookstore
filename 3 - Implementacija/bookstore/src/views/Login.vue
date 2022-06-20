<template>
    <span>
        <nav class="navbar navbar-expand-lg navbar-light bg-light bottom-border-grey">
            <a class="navbar-brand">
                &nbsp;&nbsp;
                <router-link class="routerlink" to="/">
                    <img src='../assets/logo.png'>
                </router-link>
            </a>
        </nav>
        <div class='container'>
            <br/><br/>
            <h1>PRIJAVA</h1>
            <br/>
            <h4>{{error}}</h4>
            <br/>
            <div class='row'>
                <div class='col col-12 col-sm-6 offset-md-4 col-md-4'>
                    <form>
                        <div class="form-group">
                            <input v-model='username' type="text" class="form-control" placeholder="Korisničko ime">
                        </div>
                        <div class="form-group">
                            <input v-model='password' type="password" class="form-control" placeholder="Lozinka">
                        </div>
                        <button type='button' class="btn btn-primary" @click='login()'>Prijavi se</button>
                    </form>
                </div>
            </div>
        </div>
    </span>
</template>

<script>
    import { users, BUYER, SELLER } from '@/initial_data/users.js'
    import { books } from '@/initial_data/books.js'
    import { recommendations } from '@/initial_data/recommendations.js';
    export default {
        name : 'Login',
        data() {
            return {
                username : '',
                password : '',
                users : [],
                error : ''
            }
        },
        mounted() {
            this.init();
        },
        methods: {
            login() {
                const user = this.users.find(u => u.username == this.username && u.password == this.password);
                if (user == null) {
                    this.error = 'Neispravni kredencijali.';
                    return;
                }
                
                let session = { currentUserId: user.id };
                localStorage.setItem('session', JSON.stringify(session));

                switch (user.user_type) {
                    case BUYER : this.$router.push('buyer'); break;
                    case SELLER : this.$router.push('seller'); break;
                }
            },
            init() {
                let lsUsers = localStorage.getItem('users');
                if (lsUsers == null) {
                    localStorage.setItem('users', JSON.stringify(users));
                }
                this.users = JSON.parse(localStorage.getItem('users'));

                let lsBooks = localStorage.getItem('books');
                if (lsBooks == null) {
                    localStorage.setItem('books', JSON.stringify(books));
                }

                let lsRecommendations = localStorage.getItem('recommendations');
                if (lsRecommendations == null) {
                    localStorage.setItem('recommendations', JSON.stringify(recommendations));
                }
            }
        }
    }
</script>

<style scoped>
    button {
        width: 100%;
    }

    h4 {
        color : red
    }
</style>