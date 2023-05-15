<script>
    import { isStudentLoggedIn } from '../store/studentStore.js';
    import {adminData , isAdminLoggedIn, teacherData, studentData, dataFromSession} from '../store/adminStore.js';
    let dataList = [1,2,3];

    function logOut(){
        sessionStorage.removeItem('jwtToken');
        sessionStorage.removeItem('name');
        sessionStorage.removeItem('email');
        sessionStorage.removeItem('phoneNo');
        isAdminLoggedIn.set(false);
        window.location.href = "#/login";
    }

    $: listLable = "Not Selected Yet"

    function getTeacherList(id){
        teacherData.fetchAllTeacher();
        listLable = id;
    }
    function getStudentList(id){
        listLable = id;
        studentData.fetchAllStudents();
        
    }
    function deactivateUser(email){

    }

    if(!$isAdminLoggedIn && sessionStorage.getItem('userType')=="Admin"){
        dataFromSession.setSessionDatatoStore();
        isAdminLoggedIn.set(true);
    }
</script>

{#if $isAdminLoggedIn}
<div class="flex w-full justify-between border">
    <div class="mt-20 w-full p-7">
        <div class="flex items-center justify-center">
            <h1 class="text-3xl font-bold"> Welcome to Admin Panel</h1>
        </div>

        <div class="flex justify-between">
           <div>
                <p class="text-2xl font-bold pt-5">Personal Info</p>
                <div class="flex">
                    <p class="text-xl font-semibold mr-5">Name:</p>
                    <p class="text-xl font-semibold">{$adminData.name}</p>
                </div>
                <div class="flex">
                    <p class="text-xl font-semibold mr-5">Email:</p>
                    <p class="text-xl font-semibold">{$adminData.email}</p>
                </div>
                <div class="flex">
                    <p class="text-xl font-semibold mr-5">Phone No:</p>
                    <p class="text-xl font-semibold">{$adminData.phoneNo}</p>
                </div>
           </div>
           <div on:click={()=>logOut()} class="p-2 bg-red-100 rounded-md h-10 cursor-pointer">Log Out</div>
        </div>

        
        
    </div>
</div>

<div class="flex w-full justify-start space-x-2 p-7">
    <button class="bg-blue-200 p-2 rounded-md" on:click={()=>getTeacherList("Teacher List")}> Show Teacher</button>
    <button class="bg-blue-200 p-2 rounded-md" on:click={()=>getStudentList("Student List")}> Show Student</button>
</div>

<div class="p-7 w-2/4">
    <p class="text-xl font-semibold">{listLable}</p>

    <div class="flex  border">
        <div class="w-80 border">Name</div>
        <div class="w-80 border">Email</div>
        <div class="w-80 border">Department</div>
        <div class="w-80 border">Action</div>
    </div>

    {#if listLable=="Teacher List"}
    {#each $teacherData as item,i}
    <div class="flex border">
        <div class="w-80 border">{item.name}</div>
        <div class="w-80 border">{item.email}</div>
        <div class="w-80 border">{item.departmentName}</div>
        <div class="w-80 border">
            <button class="p-2 bg-red-400 rounded-md"> Deactive</button>
        </div>
        
    </div>     
    {/each}
    {:else if listLable=="Student List"}
    {#each $studentData as item,i}
    <div class="flex border">
        <div class="w-80 border">{item.name}</div>
        <div class="w-80 border">{item.email}</div>
        <div class="w-80 border">{item.departmentName}</div>
        <div class="w-80 border">
            <button class="p-2 bg-red-400 rounded-md" on:click={()=>deactivateUser(item.email)}> Deactive</button>
        </div>
        
    </div>     
    {/each}
        
    {/if}
</div>
    
{/if}