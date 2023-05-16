<script>
    import UpdateComponent from "../components/update.svelte";
    import { showUpdateComponent } from "../store/studentStore";
    import { isTeacherLoggedIn, singleTeacherData, teacherDataFromSession } from "../store/teacherStore.js";


    function logOut(){
        sessionStorage.removeItem('jwtToken');
        sessionStorage.removeItem('name');
        sessionStorage.removeItem('email');
        sessionStorage.removeItem('phoneNo');
        sessionStorage.removeItem('teacherId');
        sessionStorage.removeItem('active');
        sessionStorage.removeItem('departmentName');
        isTeacherLoggedIn.set(false);
        window.location.href = "#/login";
    }

    
    function showUpdate(){
        if($showUpdateComponent==false){
            showUpdateComponent.set(true);
        }else {
            showUpdateComponent.set(false);
        }
        
    }

    if(!$isTeacherLoggedIn && sessionStorage.getItem('userType')=="Teacher"){
        teacherDataFromSession.setTeacherDataToStoreFromSession();
        isTeacherLoggedIn.set(true);
    }

    
    
</script>

{#if $isTeacherLoggedIn}
<div class="flex w-full justify-between border">
    <div class="mt-20 w-full p-7">
        <div class="flex items-center justify-center">
            <h1 class="text-3xl font-bold"> Welcome to Teacher Panel</h1>
        </div>

        <div class="flex justify-between">
           <div>
                <p class="text-2xl font-bold pt-5">Personal Info</p>
                <div class="flex">
                    <p class="text-xl font-semibold mr-5">Name:</p>
                    <p class="text-xl font-semibold">{$singleTeacherData.name}</p>
                </div>
                <div class="flex">
                    <p class="text-xl font-semibold mr-5">Email:</p>
                    <p class="text-xl font-semibold">{$singleTeacherData.email}</p>
                </div>
                <div class="flex">
                    <p class="text-xl font-semibold mr-5">Phone No:</p>
                    <p class="text-xl font-semibold">{$singleTeacherData.phoneNo}</p>
                </div>
           </div>
           <div>
                <p class="text-2xl font-bold pt-5">Academic Info</p>
                <div class="flex">
                    <p class="text-xl font-semibold mr-5">Teacher Id:</p>
                    <p class="text-xl font-semibold">{$singleTeacherData.teacherId}</p>
                </div>
                <div class="flex">
                    <p class="text-xl font-semibold mr-5">Department Name:</p>
                    <p class="text-xl font-semibold">{$singleTeacherData.departmentName}</p>
                </div>
                <div class="flex">
                    <p class="text-xl font-semibold mr-5">Status:</p>
                    <p class="text-xl font-semibold">{$singleTeacherData.active}</p>
                </div>
            </div>
           <div on:click={()=>logOut()} class="p-2 bg-red-100 rounded-md h-10 cursor-pointer">Log Out</div>
        </div>

    </div>
    
</div>

<div class="p-7">
    <button class="p-2 bg-blue-400 rounded-md" on:click={()=>showUpdate()}>Update</button>
</div>

{#if $showUpdateComponent}
<UpdateComponent name={$singleTeacherData.name}... email={$singleTeacherData.email}
 phoneNo={$singleTeacherData.phoneNo}... departmentName={$singleTeacherData.departmentName}... userType="Teacher"></UpdateComponent>
    
{/if}

{/if}
