<template>
    <span>
        <Nav></Nav>
        <div class='container'>
            <div class='row'>
                <div class='col-12 offset-md-2 col-md-8 offset-lg-3 col-lg-6'>
                    <h1 style='padding-top: 35px; padding-bottom : 35px'>UNESI NOVU KNJIGU</h1>
                    <form>
                        <table width='100%'>
                            <tr>
                                <td colspan="2">
                                    <h4 id='error'>{{error}}</h4>
                                    <h4 id='success'>{{success}}</h4>
                                </td>
                            </tr>
                            <tr>
                                <td width='40%' align='right' style='padding-right: 15px'><h4>Naziv:</h4></td>
                                <td width='60%' align='left' style='padding-left: 15px'>
                                    <div class="form-group">
                                        <input v-model='book.name' type="text" class="form-control" placeholder="Naziv">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td width='40%' align='right' style='padding-right: 15px'><h4>Autor:</h4></td>
                                <td width='60%' align='left' style='padding-left: 15px'>
                                    <div class="form-group">
                                        <input v-model='book.author' type="text" class="form-control" placeholder="Autor">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td width='40%' align='right' style='padding-right: 15px'><h4>Godina izdanja:</h4></td>
                                <td width='60%' align='left' style='padding-left: 15px'>
                                    <div class="form-group">
                                        <input v-model='book.release_year' type="number" class="form-control" placeholder="Godina izdanja">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td width='40%' align='right' style='padding-right: 15px'><h4>Broj strana:</h4></td>
                                <td width='60%' align='left' style='padding-left: 15px'>
                                    <div class="form-group">
                                        <input v-model='book.page_count' type="number" class="form-control" placeholder="Broj strana">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td width='40%' align='right' style='padding-right: 15px'><h4>Opis:</h4></td>
                                <td width='60%' align='left' style='padding-left: 15px'>
                                    <div class="form-group">
                                        <textarea class="form-control" v-model='book.description' rows="4"></textarea>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td width='40%' align='right' style='padding-right: 15px'><h4>Slika:</h4></td>
                                <td width='60%' align='left' style='padding-left: 15px'>
                                    <div class="form-group">
                                        <input type="file" id="imgFile" name="filename" @change="handleFileChange($event)">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" align='center' style='padding-top: 35px'>
                                    <button style='width: 40%;' type='button' class="btn btn-primary" @click='addBook()'>Dodaj knjigu</button>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </span>
</template>

<script>
    import Nav from '@/components/Seller/Nav.vue'
    import { SELLER } from '@/initial_data/users.js'
    import { checkValidUser, logout, isEmptyOrNull } from '@/utils/utils.js'
    //import $ from 'jquery'

    export default {
        name : 'AddBook',
        components : {
            Nav
        },
        data(){
            return {
                error : '',
                success : '',
                book : {
                    id : '',
                    name : '',
                    author : '',
                    release_year : '',
                    page_count : '',
                    description : '',
                    imgSrc : '',
                    promo : false,
                    reviews : []
                }
            }
        },
        mounted() {
            if (checkValidUser(SELLER) == false) {
                logout(this.$router);
                return;
            }
        },
        methods : {
            handleFileChange(evt) {
                var fileData =  evt.target.files[0];
                this.book.imgSrc = fileData.name;
            },
            addBook() {
                let isAnythingNullOrEmpty = false;
                isAnythingNullOrEmpty ||= isEmptyOrNull(this.book.name);
                isAnythingNullOrEmpty ||= isEmptyOrNull(this.book.author);
                isAnythingNullOrEmpty ||= isEmptyOrNull(this.book.release_year);
                isAnythingNullOrEmpty ||= isEmptyOrNull(this.book.page_count);
                isAnythingNullOrEmpty ||= isEmptyOrNull(this.book.imgSrc);

                if (isAnythingNullOrEmpty) {
                    this.error = 'Popunite sva polja.';
                    this.success = '';
                    return;
                }

                let books = JSON.parse(localStorage.getItem('books'));

                const newId = Math.max.apply(Math, books.map(b => b.id)) + 1;
                this.book.id = newId;
                books.push(JSON.parse(JSON.stringify(this.book)));
                localStorage.setItem('books', JSON.stringify(books));

                this.error = '';
                this.success = 'Uspešno ste dodali knjigu!';
            }
        }
    }
</script>

<style scoped>
</style>