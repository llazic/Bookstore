<template>
    <nav class="navbar navbar-expand-lg navbar-light bg-light bottom-border-grey">
        <a class="navbar-brand">
            &nbsp;&nbsp;
            <router-link class="routerlink" to="/buyer">
                <img src='@/assets/logo.png'>
            </router-link>
        </a>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <div class='navbar-menu ml-auto'>
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link">
                            <router-link class="routerlink" to="/buyer/recommendations">PREPORUČENE KNJIGE</router-link>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link">
                            <router-link class="routerlink" to="/buyer/profile">PROFIL</router-link>
                        </a>
                    </li>
                    <li>
                        <a class="nav-link routerlink">
                            <router-link class="routerlink" to="/">ODJAVI SE</router-link>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</template>

<script>
    export default{
        name: 'Nav',
        methods : {
        }
    }
</script>


<style>

    .routerlink {
        text-decoration: none;
        font-weight: bold;
        font-size: 25px;
    }

    li{
        padding-right: 10px;
    }
</style>
