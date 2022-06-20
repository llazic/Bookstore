<template>
    <div class="card">
        <img class="card-img-top" :src="'/books/' + book.imgSrc" alt="Card image cap">
        <div class="card-body">
            <h3 class="card-title">{{book.name}}</h3>
            <h5 class="card-text">{{book.author}}</h5>
        </div>
    </div>
</template>


<script>
    export default{
        name : 'BookPreview',
        props : [
            'book'
        ]
    }
</script>


<style scoped>
    .card { 
        align-items: center;
        width : 300px;
        border-color: white;
    }
    img {
        width : 200px;
        height: 316px;
        padding-top: 30px;
    }
    .card-body {
        padding-top: 20px;
    }


</style>