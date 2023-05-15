<script>
    import {adminData , isLoggedIn} from '../store/adminStore.js';
    import { studentProfileData } from '../store/studentStore.js';
    
    function logOut(){
        isLoggedIn.set(false);
        window.location.href = "#/login";
    }

</script>

{#if $isLoggedIn}
<div class="flex w-full justify-between border">
    <div class="mt-20 w-full p-7">
        <div class="flex items-center justify-center">
            <h1 class="text-3xl font-bold"> Welcome to Student Panel</h1>
        </div>

        <div class="flex justify-between">
           <div>
                <p class="text-2xl font-bold pt-5">Personal Info</p>
                <div class="flex">
                    <p class="text-xl font-semibold mr-5">Name:</p>
                    <p class="text-xl font-semibold">{$studentProfileData.name}</p>
                </div>
                <div class="flex">
                    <p class="text-xl font-semibold mr-5">Email:</p>
                    <p class="text-xl font-semibold">{$studentProfileData.email}</p>
                </div>
                <div class="flex">
                    <p class="text-xl font-semibold mr-5">Phone No:</p>
                    <p class="text-xl font-semibold">{$studentProfileData.phoneNo}</p>
                </div>
           </div>
           <div>
                <p class="text-2xl font-bold pt-5">Academic Info</p>
                <div class="flex">
                    <p class="text-xl font-semibold mr-5">Student Id:</p>
                    <p class="text-xl font-semibold">{$studentProfileData.studentId}</p>
                </div>
                <div class="flex">
                    <p class="text-xl font-semibold mr-5">Department Name:</p>
                    <p class="text-xl font-semibold">{$studentProfileData.departmentName}</p>
                </div>
                <div class="flex">
                    <p class="text-xl font-semibold mr-5">Status:</p>
                    <p class="text-xl font-semibold">{$studentProfileData.active}</p>
                </div>
            </div>
           <div on:click={()=>logOut()} class="p-2 bg-red-100 rounded-md h-10 cursor-pointer">Log Out</div>
        </div>

        
        
    </div>
</div>


{/if}