<template>
    <tr>
        <td align='left' style='border-bottom: 1px solid black;'>
            <a class="routerlink font_size">
                <router-link class="routerlink font_size" :to="/book/+recommendation.book_id">
                    {{books.find(b => b.id == recommendation.book_id).name}}
                </router-link>
            </a>
        </td>
        <td align='right' style='border-bottom: 1px solid black;'>
            <h5 class='font_size'>
                {{recommendation.from}}
            </h5>
        </td>
    </tr>
</template>

<script>
    export default {
        name : 'Recommendation',
        props : [
            'recommendation'
        ],
        data() {
            return {
                books : []
            }
        },
        mounted() {
            this.books = JSON.parse(localStorage.getItem('books'));
        }
    }
</script>

<style scoped>
    .font_size {
        font-size: 30px !important;
        font-weight: normal;
    }
</style>


