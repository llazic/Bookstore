<template>
    <span>
        <Nav/>
        <div class='container'>
            <BookSection :sectionName="'PROMOCIJA'"></BookSection>
            <BookSection :sectionName="'SVE KNJIGE'"></BookSection>
        </div>
    </span>
</template>

<script>
    import { logout, checkValidUser } from '@/utils/utils.js';
    import { BUYER } from '@/initial_data/users.js';
    import BookSection from '@/components/Buyer/BookSection.vue'
    import Nav from '@/components/Buyer/Nav.vue'

    export default {
        name : 'BuyerLanding',
        mounted() {
            if (checkValidUser(BUYER) == false) {
                logout(this.$router);
                return;
            }
        },
        components : {
            BookSection,
            Nav
        }
    }
</script>


<style scoped>
</style>


