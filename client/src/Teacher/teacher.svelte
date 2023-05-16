<script>
    import ResetCompnent from "../components/resetCompnent.svelte";
    import UpdateComponent from "../components/update.svelte";
    
    import { showUpdateComponent, showResetComponent } from "../store/studentStore";
    import { isTeacherLoggedIn, singleTeacherData, teacherDataFromSession, teacherWiseStudentList,requestList } from "../store/teacherStore.js";


    function logOut(){
        sessionStorage.removeItem('userType');
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
        showResetComponent.set(false);
        if($showUpdateComponent==false){
            showUpdateComponent.set(true);
        }else {
            showUpdateComponent.set(false);
        }
        
    }

    function showReset(){
        showUpdateComponent.set(false);
        if($showResetComponent==false){
            showResetComponent.set(true); 
        }else{
            showResetComponent.set(false);
        }
    }

    async function removeStudent(studentEmail, teacherEmail){
        const jwtToken = sessionStorage.getItem('jwtToken');
        let url ='http://localhost:8080/removeStudentFromTeacher?studentEmail='+studentEmail+"&teacherEmail="+teacherEmail;

        let response = await fetch(url, {
            method: 'PATCH',
            headers: {
                'Authorization': `Bearer ${jwtToken}`,
            },
        });
        const data = await response.json();
        if(data["success"]){
            teacherWiseStudentList.getTeacherWiseStudentData(teacherEmail);
        }else{
            alert("Unable to delete!!!")
        }

    }

    async function approveRequest(studentEmail,teacherEmail){
        const jwtToken = sessionStorage.getItem('jwtToken');
        let url ='http://localhost:8080/approveRequest?studentEmail='+studentEmail;

        let response = await fetch(url, {
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${jwtToken}`,
            },
        });
        const data = await response.json();
        if(data["success"]){
            requestList.getRequestData(teacherEmail);
        }else{
            alert("Unable to aprove!!!")
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

<div class="p-7 flex">
    <button class="p-2 bg-blue-400 rounded-md" on:click={()=>showUpdate()}>Update</button>
    <button class="p-2 bg-blue-400 rounded-md ml-5" on:click={()=>showReset()}>Reset Password</button>
</div>

{#if $showUpdateComponent}
<UpdateComponent name={$singleTeacherData.name}... email={$singleTeacherData.email}
 phoneNo={$singleTeacherData.phoneNo}... departmentName={$singleTeacherData.departmentName}... userType="Teacher"></UpdateComponent>
    
{/if}

{#if $showResetComponent}
<ResetCompnent/>
{/if}

<div class="p-7 w-full flex">
    <div class="w-1/2 border">
        <p class="text-2xl font-bold">Student List</p>
        <button class="p-2 bg-blue-400 rounded-md" on:click={()=>teacherWiseStudentList.getTeacherWiseStudentData($singleTeacherData.email)}>Show Students</button>

        <div class="flex border">
            <div class="w-80 border">Name</div>
            <div class="w-80 border">Email</div>
            <div class="w-80 border">Department</div>
            <div class="w-80 border">Action</div>
        </div>
        {#each $teacherWiseStudentList as item}
        <div class="flex border">
            <div class="w-80 border">{item.name}</div>
            <div class="w-80 border">{item.email}</div>
            <div class="w-80 border">{item.departmentName}</div>
            <div class="w-80 border">
                <button class="p-2 bg-red-400 rounded-md" on:click={()=>removeStudent(item.email,$singleTeacherData.email)}> Remove</button>
            </div>       
        </div>     
        {/each}
    </div>

    <div class="w-1/2 border">
        <p class="text-2xl font-bold">Request List</p>
        <button class="p-2 bg-blue-400 rounded-md" on:click={()=>requestList.getRequestData($singleTeacherData.email)}>Show Request</button>
        <div class="flex border">
            <div class="w-80 border">Name</div>
            <div class="w-80 border">Email</div>
            <div class="w-80 border">Action</div>
        </div>
        {#each $requestList as item}
        <div class="flex border">
            <div class="w-80 border">{item.studentName}</div>
            <div class="w-80 border">{item.studentemail}</div>
            <div class="w-80 border">
                <button class="p-2 bg-red-400 rounded-md" on:click={()=>{approveRequest(item.studentemail,$singleTeacherData.email)}}> Aprove</button>
            </div>       
        </div>     
        {/each}
        
    </div>

</div>

{/if}
