<template>
    <form>
        <div class='row'>
            <div class='col-12 offset-md-2 col-md-8 offset-lg-3 col-lg-6'>
                <form>
                    <div class='form-group'>
                        <input v-model='searchTerm' type="text" class="form-control" placeholder="Pretraži knjige..." @input="search()">
                    </div>
                </form>
            </div>
        </div>
        <div class='row'>
            <div class='offset-2 col-8 offset-md-0 col-md-4 col-lg-3 bottom_padding' v-for='book in selectedBooks' :key="book.id">
                <div class="card">
                    <img class="card-img-top book-card-img" :src="'/books/' + book.imgSrc" alt="Card image cap">
                    <div class="card-body">
                        <h3 class="card-title">{{book.name}}</h3>
                        <h5 class="card-text">{{book.author}}</h5>
                        <button v-if='book.promo' type='button' class="btn btn-danger" @click='togglePromo(book.id)'>Ukloni sa promo</button>
                        <button v-else type='button' class="btn btn-success" @click='togglePromo(book.id)'>Dodaj na promo</button>
                    </div>
                </div>
            </div>
            <hr>
        </div>
    </form>
</template>

<script>
    export default {
        name : 'BookSection',
        data() {
            return {
                allBooks : [],
                selectedBooks : [],
                searchTerm : ''
            }
        },
        mounted() {
            this.allBooks = JSON.parse(localStorage.getItem('books'));
            this.selectedBooks = this.allBooks;
        },
        methods : {
            search() {
                this.selectedBooks = this.allBooks.filter(
                    b => b.name.toLowerCase().includes(this.searchTerm.toLowerCase()) 
                    || b.author.toLowerCase().includes(this.searchTerm.toLowerCase())
                );
            },
            togglePromo(book_id) {
                let book = this.allBooks.find(b => b.id == book_id);
                book.promo = !book.promo;

                localStorage.setItem('books', JSON.stringify(this.allBooks));
            }
        },
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
    
    .card { 
        align-items: center;
        width : 300px;
        border-color: white;
    }
    .book-card-img {
        width : 200px;
        height: 316px;
        padding-top: 30px;
    }
    .card-body {
        padding-top: 20px;
    }
</style>
