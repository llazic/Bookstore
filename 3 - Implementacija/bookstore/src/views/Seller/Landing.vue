<template>
    <span>
        <Nav></Nav>
        <div class='container'>
            <BookSection></BookSection>
        </div>
    </span>
</template>


<script>
    import Nav from '@/components/Seller/Nav.vue'
    import BookSection from '@/components/Seller/BookSection.vue'
    import { checkValidUser, logout } from '@/utils/utils.js'
    import { SELLER } from '@/initial_data/users.js'

    export default {
        name : 'Landing',
        mounted() {
            if (checkValidUser(SELLER) == false) {
                logout(this.$router);
                return;
            }
        },
        components : {
            Nav,
            BookSection
        }
    }
</script>

<style scoped>
</style>