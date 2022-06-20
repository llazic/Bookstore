<template>
    <div class='row'>
        <h1>{{sectionName}}</h1>
        <div class='offset-2 col-8 offset-md-0 col-md-4 col-lg-3 bottom_padding' v-for='book in selectedBooks' :key="book.id">
            <router-link class="routerlink" :to='/book/+book.id'>
                <BookPreview :book='book'></BookPreview>
            </router-link>
        </div>
        <hr>
    </div>
</template>

<script>
import BookPreview from '@/components/Buyer/BookPreview.vue'
import { books } from '@/initial_data/books.js'

export default {
    name : 'BookSection',
    props : [
        'sectionName'
    ],
    components : {
        BookPreview
    },
    data(){
        return {
            allBooks : [],
            selectedBooks : []
        }
    },
    mounted() {
        let allBooks = localStorage.getItem('books');
        if (allBooks == null) this.allBooks = books;
        else this.allBooks = JSON.parse(allBooks);

        this.selectedBooks = this.sectionName == 'PROMOCIJA' ? this.allBooks.filter(b => b.promo) : this.allBooks.filter(b => !b.promo);
    }
}
</script>

<style scoped>
    img {
        width : 30%;
    }
    .row {
        padding-top: 20px;
    }
    .bottom_padding {
        padding-bottom: 20px;
    }
    h1 {
        font-weight: bold;
    }
</style>
