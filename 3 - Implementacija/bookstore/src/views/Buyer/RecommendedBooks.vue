<template>
    <span>
        <Nav></Nav>
        <div class='container'>
            <h1 style='padding-top: 35px; padding-bottom : 35px'>PREPORUČENE KNJIGE</h1>
            <div class='row'>
                <div class='col-12 offset-md-2 col-md-8 offset-lg-3 col-lg-6'>
                    <table width='100%'>
                        <tr>
                            <td colspan="2" style='border-bottom: 1px solid black;'>

                            </td>
                        </tr>
                        <Recommendation v-for='rec in recommendations' :key="rec.from + ' ' + rec.book_id" :recommendation='rec'></Recommendation>
                    </table>
                </div>
            </div>
        </div>
    </span>
</template>

<script>
    import Nav from '@/components/Buyer/Nav.vue';
    import Recommendation from '@/components/Buyer/Recommendation.vue';
    import { BUYER } from '@/initial_data/users.js'
    import { checkValidUser, getSession, logout } from '@/utils/utils.js'
    export default {
        name : 'RecommendedBooks',
        data() {
            return {
                recommendations : []
            }
        },
        mounted() {
            if (checkValidUser(BUYER) == false) {
                logout(this.$router);
                return;
            }

            const currentUserId = getSession().currentUserId;
            const users = JSON.parse(localStorage.getItem('users'));
            const user = users.find(u => u.id == currentUserId);
            const allRecommendations = JSON.parse(localStorage.getItem('recommendations'));
            this.recommendations = allRecommendations.filter(r => r.to == user.username);
        },
        components : {
            Nav,
            Recommendation
        }
    }
</script>

<style scoped>

</style>
