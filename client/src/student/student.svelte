<script>
    import { teacherData } from '../store/adminStore.js';
    import { studentProfileData, isStudentLoggedIn, studentDataFromSession, showUpdateComponent, showResetComponent } from '../store/studentStore.js';
    import UpdateComponent from '../components/update.svelte';
    import ResetCompnent from '../components/resetCompnent.svelte';
    
    teacherData.fetchAllTeacher();
    function logOut(){
        sessionStorage.removeItem('userType');
        sessionStorage.removeItem('jwtToken');
        sessionStorage.removeItem('name');
        sessionStorage.removeItem('email');
        sessionStorage.removeItem('phoneNo');
        sessionStorage.removeItem('studentId');
        sessionStorage.removeItem('active');
        sessionStorage.removeItem('departmentName');
        isStudentLoggedIn.set(false);
        window.location.href = "#/login";
    }

    // let showUpdateComponent = false;

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


    async function onSubmit() {  
        let selectedTeacherEmail = document.querySelector('input[name="selectedTeacher"]:checked').value;
        console.log('Selected Teacher:', selectedTeacherEmail);
        let studentEmail = sessionStorage.getItem('email');
        let studentName  = sessionStorage.getItem('name');

        let plainObject = {
            studentEmail: studentEmail,
            teacherEmail: selectedTeacherEmail,
            studentName: studentName
        };
        const jwtToken = sessionStorage.getItem('jwtToken');
        const jsonData = JSON.stringify(plainObject);

        let response = await fetch('http://localhost:8080/requestForAdvisor', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${jwtToken}`,
            },
            body: jsonData
        });

        const data = await response.json();
        // console.log(data);

        if(data["success"]){
            alert("Successfully request sent!!");
        }else{
            alert("Failed request sent!!");
        }


    }


    if(!$isStudentLoggedIn && sessionStorage.getItem('userType')=="Student"){
        studentDataFromSession.setStudentDataToStoreFromSession();
        isStudentLoggedIn.set(true);
    }

</script>

{#if $isStudentLoggedIn}
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

<div class="p-7">
    <button class="p-2 bg-blue-400 rounded-md" on:click={()=>showUpdate()}>Update</button>
    <button class="p-2 bg-blue-400 rounded-md ml-5" on:click={()=>showReset()}>Reset Password</button>
</div>

{#if $showUpdateComponent}
<UpdateComponent name={$studentProfileData.name}... email={$studentProfileData.email}
 phoneNo={$studentProfileData.phoneNo}... departmentName={$studentProfileData.departmentName}... userType="Student"></UpdateComponent>
    
{/if}

{#if $showResetComponent}
<ResetCompnent/>
{/if}

<div class="p-7">
    <p class="bg-gray-100 w-full mb-3 font-bold"> Select Teacher and Send Request For Advising</p>
    <form id="myForm" on:submit|preventDefault={onSubmit}>
        {#each $teacherData as item}
        <input type="radio" id={item.teacherId} name="selectedTeacher" value={item.email}>
        <label class="p-2" for="name">{item.name}</label>
        <label class="p-2" for="email">{item.email}</label>
        <label class="p-2" for="departmentName">{item.departmentName}</label>
        <br>
        {/each}
        <button class="bg-blue-400 rounded-md p-2 mt-3" type="submit">Send Request</button>  
    </form>
</div>


{/if}