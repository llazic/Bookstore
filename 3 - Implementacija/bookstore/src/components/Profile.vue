<template>
    <form>
        <table width='100%'>
            <tr>
                <td colspan="2">
                    <h4 id='error'>{{error}}</h4>
                    <h4 id='success'>{{success}}</h4>
                </td>
            </tr>
            <tr>
                <td width='40%' align='right' style='padding-right: 15px'><h4>Korisničko ime:</h4></td>
                <td width='60%' align='left' style='padding-left: 15px'><h4>{{user.username}}</h4></td>
            </tr>
            <tr>
                <td width='40%' align='right' style='padding-right: 15px'><h4>Ime:</h4></td>
                <td width='60%' align='left' style='padding-left: 15px'>
                    <div class="form-group">
                        <input v-model='user.name' type="text" class="form-control" placeholder="Ime">
                    </div>
                </td>
            </tr>
            <tr>
                <td width='40%' align='right' style='padding-right: 15px'><h4>Prezime:</h4></td>
                <td width='60%' align='left' style='padding-left: 15px'>
                    <div class="form-group">
                        <input v-model='user.surname' type="text" class="form-control" placeholder="Prezime">
                    </div>
                </td>
            </tr>
            <tr>
                <td width='40%' align='right' style='padding-right: 15px'><h4>Kontakt telefon:</h4></td>
                <td width='60%' align='left' style='padding-left: 15px'>
                    <div class="form-group">
                        <input v-model='user.phone_number' type="number" class="form-control" placeholder="Telefon">
                    </div>
                </td>
            </tr>
            <tr>
                <td width='40%' align='right' style='padding-right: 15px'><h4>Adresa:</h4></td>
                <td width='60%' align='left' style='padding-left: 15px'>
                    <div class="form-group">
                        <input v-model='user.address' type="text" class="form-control" placeholder="Adresa">
                    </div>
                </td>
            </tr>
            <tr>
                <td width='40%' align='right' style='padding-right: 15px'><h4>Promeni lozinku:</h4></td>
                <td width='60%' align='left' style='padding-left: 15px'>
                    <div class="form-check">
                        <input id='change-password' class="form-check-input" type="checkbox" value="" @change="checkboxChanged()">
                    </div>
                </td>
            </tr>
            <tr>
                <td width='40%' align='right' style='padding-right: 15px'><h4>Nova lozinka:</h4></td>
                <td width='60%' align='left' style='padding-left: 15px'>
                    <div class="form-group">
                        <input v-model='new_password_1' type="password" class="form-control new-password" placeholder="Nova lozinka" disabled>
                    </div>
                </td>
            </tr>
            <tr>
                <td width='40%' align='right' style='padding-right: 15px'><h4>Nova lozinka:</h4></td>
                <td width='60%' align='left' style='padding-left: 15px'>
                    <div class="form-group">
                        <input v-model='new_password_2' type="password" class="form-control new-password" placeholder="Nova lozinka" disabled>
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="2" align='center' style='padding-top: 35px'>
                    <button style='width: 40%;' type='button' class="btn btn-primary" @click='saveChanges()'>Sačuvaj izmene</button>
                </td>
            </tr>
        </table>
    </form>
</template>


<script>
    import { getSession, isEmptyOrNull } from '@/utils/utils.js'
    import $ from 'jquery'

    export default {
        name : 'Profile',
        data() {
            return {
                user : {},
                users : [],
                new_password_1: '',
                new_password_2: '',
                error : '',
                success : ''
            }
        },
        mounted() {
            this.users = JSON.parse(localStorage.getItem('users'));
            const currentUserId = getSession().currentUserId;
            this.user = this.users.find(u => u.id == currentUserId);
        },
        methods: {
            checkboxChanged() {
                if ($('#change-password')[0].checked) {
                    $('.new-password').removeAttr('disabled')
                } else {
                    $('.new-password').attr('disabled', true)
                }
            },
            saveChanges() {
                let isAnythingEmptyOrNull = false;
                isAnythingEmptyOrNull ||= isEmptyOrNull(this.user.name);
                isAnythingEmptyOrNull ||= isEmptyOrNull(this.user.surname);
                isAnythingEmptyOrNull ||= isEmptyOrNull(this.user.phone_number);
                isAnythingEmptyOrNull ||= isEmptyOrNull(this.user.address);
                if ($('#change-password')[0].checked) {
                    isAnythingEmptyOrNull ||= isEmptyOrNull(this.new_password_1);
                    isAnythingEmptyOrNull ||= isEmptyOrNull(this.new_password_2);
                }
                if (isAnythingEmptyOrNull) {
                    this.error = 'Popunite sva polja.';
                    this.success = '';
                    return;
                }

                if ($('#change-password')[0].checked && this.new_password_1 != this.new_password_2) {
                    this.error = 'Lozinke se ne poklapaju.';
                    this.success = '';
                    return;
                }

                this.user.password = this.new_password_1;
                const oldUserIndex = this.users.findIndex(u => u.id == this.user.id);
                this.users.splice(oldUserIndex, 1);
                
                // Copy user and add it to users.
                this.users.push(JSON.parse(JSON.stringify(this.user)));

                localStorage.setItem('users', JSON.stringify(this.users));
                this.error = '';
                this.success = 'Uspešno ste sačuvali izmene!';
            }
        }
    }
</script>

<style scoped>
    #error {
        color: red;
    }
    #success {
        color: green;
    }
</style>
