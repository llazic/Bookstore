<template>
    <table width='100%' height='170px'>
        <tr>
            <td align='left' style='vertical-align: top; padding-top: 10px'>
                <img v-for='index in review.rating' :key='index' src='@/assets/star.png' style='width: 3%'>
            </td>
            <td align='right' style='vertical-align: top; padding-top: 10px'>
                <h5>{{review.user}}</h5>
            </td>
        </tr>
        <tr style='border-bottom: 1px solid black;'>
            <td colspan='2' align='left' style='vertical-align: bottom'>
                <h5>{{review.comment}}</h5>
            </td>
        </tr>
    </table>
</template>

<script>
    export default {
        name : 'Review',
        props : [
            'review'
        ]
    }
</script>

<style scoped>

</style>
