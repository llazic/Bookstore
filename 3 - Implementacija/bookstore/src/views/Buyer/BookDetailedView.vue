<template>
    <span>
        <Nav></Nav>
        <div class='container'>
            <div class='row' style='padding-top : 70px'>
                <div class='col-12 col-xl-3 offset-xl-1'>
                    <table width='100%'>
                        <tr>
                            <td width='100%' align='center'>
                                <img :src="'/books/' + book.imgSrc" class='book_img'>
                            </td>
                        </tr>
                        <tr>
                            <td style='padding-top: 15px'>
                                <h4 style='font-weight: bold;'>Godina izdanja: {{book.release_year}}</h4>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h4 style='font-weight: bold;'>Broj strana: {{book.page_count}}</h4>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class='col-12 col-xl-8'>
                    <table style='width: 100%'>
                        <tr>
                            <td class='align-left'>
                                <h1>{{book.name}}</h1>
                            </td>
                        </tr>
                        <tr>
                            <td class='align-left'>
                                <h3>{{book.author}}</h3>
                            </td>
                        </tr>
                        <tr>
                            <table style='width: 100%'>
                                <tr style='border-bottom: 1px solid black;'>
                                    <td style='padding-top: 35px' align='left' width='33%'>
                                        <a @click='show_description()'><h4 class='description_link bold-class'>Opis</h4></a>
                                    </td>
                                    <td style='padding-top: 35px' align='center' text-align='center' width='33%'>
                                        <a @click='show_reviews()'><h4 class='reviews_link'>Recenzije</h4></a>
                                    </td>
                                    <td style='padding-top: 35px' align='right' width='33%'>
                                        <a @click='show_recommend()'><h4 class='recommend_link'>Preporuči</h4></a>
                                    </td>
                                </tr>
                                <tr>
                                    <td v-if='tab === DESCRIPTION_TAB' style='padding-top: 35px' colspan="3" class='description'>
                                        <h5>{{book.description}}</h5>
                                    </td>
                                    <td v-if='tab === REVIEWS_TAB' colspan="3">
                                        <span v-for='review in book.reviews' :key="review.user">
                                            <Review :review='review'></Review>
                                        </span>
                                        <form>
                                            <table width='100%'>
                                                <tr align='center'>
                                                    <td style='padding-top: 35px'>
                                                        <h4 style='font-weight: bold;'>Ostavite recenziju</h4>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <h5 style='color : red'>{{error}}</h5>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align='left'>
                                                        <img v-for='index in 5' :key='index' src='@/assets/white_star.png' style='width: 3%' :id="'star'+index" @click='rate(index)'>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td style='padding-top : 10px'>
                                                        <div class="form-group">
                                                            <textarea class="form-control" id="exampleFormControlTextarea1" rows="4" v-model='review.comment'></textarea>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align='right'>
                                                        <button type='button' class="btn btn-primary" @click='postReview()'>POSTAVI RECENZIJU</button>
                                                    </td>
                                                </tr>
                                            </table>
                                        </form>
                                    </td>
                                    <td v-if='tab === RECOMMEND_TAB' style='padding-top: 35px' colspan="3" align='center'>
                                        <table width='80%'>
                                            <tr>
                                                <td colspan="2">
                                                    <form>
                                                        <div class="form-group">
                                                            <input v-model='searchTerm' @input='filterSearchedUsers' type="text" class="form-control" placeholder="Pretražite korisnike">
                                                        </div>
                                                    </form>
                                                </td>
                                            </tr>
                                            <tr v-for='user in searchedUsers' :key='user.id'>
                                                <td style='border-bottom: 1px solid black; padding-top : 5px; padding-bottom : 5px; vertical-align: bottom' align='left'>
                                                    <h5>{{user.username}}</h5>
                                                </td>
                                                <td style='border-bottom: 1px solid black; padding-top : 5px; padding-bottom : 5px' align='right'>
                                                    <button v-if='recommendationExists(user.username) == false' type='button' class="btn btn-primary" @click='recommend(user.username)'>PREPORUČI</button>
                                                    <img v-else src='@/assets/check.png' style='width : 7%; margin-right: 35px'>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                            <br/><br/><br/><br/>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </span>
</template>

<script>
    import Nav from '@/components/Buyer/Nav.vue'
    import $ from 'jquery'
    import Review from '@/components/Buyer/Review.vue'
    import { checkValidUser, logout, getSession } from '@/utils/utils.js';
    import { BUYER } from '@/initial_data/users.js';

    export default {
        name : 'BookDetailedView',
        data() {
            return {
                books : {},
                book : {},
                DESCRIPTION_TAB : 1,
                REVIEWS_TAB : 2,
                RECOMMEND_TAB : 3,
                tab : this.DESCRIPTION_TAB,
                review : {
                    rating : null,
                    comment : '',
                    user : null
                },
                error : '',
                users : [],
                currentUser : {},
                searchTerm : '',
                searchedUsers : [],
                recommendations : []
            }
        },
        mounted() {
            if (checkValidUser(BUYER) == false) {
                logout(this.$router);
                return;
            }

            this.books = JSON.parse(localStorage.getItem('books'));
            let book_id = Number(this.$route.params.id);
            this.book = this.books.find(b => b.id == book_id);
            if (this.book == null) {
                this.$router.push('/buyer');
                return;
            }

            this.tab = this.DESCRIPTION_TAB

            this.recommendations = JSON.parse(localStorage.getItem('recommendations'));

            this.users = JSON.parse(localStorage.getItem('users'));
            this.currentUser = this.users.find(u => u.id == getSession().currentUserId);
            this.filterSearchedUsers();
        },
        methods: {
            recommend(to){
                this.recommendations.push({
                    from : this.currentUser.username,
                    to : to,
                    book_id : this.book.id
                });
                localStorage.setItem('recommendations', JSON.stringify(this.recommendations));
            },
            recommendationExists(username) {
                let index = this.recommendations.findIndex(r => r.from===this.currentUser.username && r.to===username && r.book_id===this.book.id);
                return index !== -1;
            },
            filterSearchedUsers() {
                this.searchedUsers = this.users.filter(u => u.id != this.currentUser.id && u.user_type == BUYER &&
                                                        u.username.toLowerCase().includes(this.searchTerm.toLowerCase()));
            },
            rate(rating) {
                this.review.rating = rating;
                this.adjustStars(rating);
            },
            adjustStars(rating){
                for (let i = 1; i <= 5; i++) {
                    $('#star'+i).attr('src', '/' + (i <= rating ? 'star.png' : 'white_star.png'));
                }
            },
            postReview() {
                if (this.review.rating == null) {
                    this.error = 'Niste ocenili knjigu.';
                    return;
                }
                
                let index = this.book.reviews.findIndex(r => r.user == this.currentUser.username);
                if (index != -1) this.book.reviews.splice(index, 1);
                this.book.reviews.push({
                    rating : this.review.rating,
                    comment : this.review.comment,
                    user : this.currentUser.username
                });

                index = this.books.findIndex(b => b.id == this.book.id);
                this.books.splice(index, 1);
                this.books.push(this.book);
                localStorage.setItem('books', JSON.stringify(this.books));

                this.review.comment = '';
                this.review.rating = null;
                this.adjustStars(0);
            },
            removeDescriptionClasses() {
                $('.description_link').removeClass('bold-class')
            },
            removeReviewClasses() {
                $('.reviews_link').removeClass('bold-class')
            },
            removeRecommendClasses() {
                $('.recommend_link').removeClass('bold-class')
            },
            addDescriptionClasses() {
                $('.content').addClass('description')
                $('.description_link').addClass('bold-class')
            },
            addReviewClasses(){
                $('.reviews_link').addClass('bold-class')
            },
            addRecommendClasses(){
                $('.recommend_link').addClass('bold-class')
            },
            show_description() {
                this.removeReviewClasses();
                this.removeRecommendClasses();
                this.addDescriptionClasses();
                this.tab = this.DESCRIPTION_TAB;
            },
            show_reviews() {
                this.removeDescriptionClasses();
                this.removeRecommendClasses();
                this.addReviewClasses();
                this.tab = this.REVIEWS_TAB;
            },
            show_recommend() {
                this.removeDescriptionClasses();
                this.removeReviewClasses();
                this.addRecommendClasses();
                this.tab = this.RECOMMEND_TAB;
            }
        },
        components : {
            Nav,
            Review
        }
    }
</script>

<style scoped>
    .book_img {
        width : 280px
    }

    h1, h3 {
        font-weight: bold;
    }

    .align-left {
        text-align: left; 
    }

    .bold-class {
        font-weight: bold;
    }

    .description {
        text-align: left;
    }
</style>
