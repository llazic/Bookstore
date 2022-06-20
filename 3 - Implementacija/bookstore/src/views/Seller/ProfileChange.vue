<template>
    <span>
        <Nav></Nav>
        <div class='container'>
            <div class='row'>
                <div class='col-12 offset-md-2 col-md-8 offset-lg-3 col-lg-6'>
                    <h1 style='padding-top: 35px; padding-bottom : 35px'>PROFIL</h1>
                    <Profile></Profile>
                </div>
            </div>
        </div>
    </span>
</template>


<script>
    import Nav from '@/components/Seller/Nav';
    import Profile from '@/components/Profile';
    import { SELLER } from '@/initial_data/users.js'
    import { checkValidUser, logout } from '@/utils/utils.js'

    export default {
        name : 'ProfileChange',
        mounted() {
            if (checkValidUser(SELLER) == false) {
                logout(this.$router);
                return;
            }
        },
        components : {
            Nav,
            Profile
        }
    }
</script>

<style scoped>
</style>

