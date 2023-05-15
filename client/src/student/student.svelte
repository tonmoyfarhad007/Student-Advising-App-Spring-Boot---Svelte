<script>
    import { teacherData } from '../store/adminStore.js';
    import { studentProfileData, isStudentLoggedIn, studentDataFromSession } from '../store/studentStore.js';
    
    teacherData.fetchAllTeacher();
    function logOut(){
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


    async function onSubmit() {  
        let selectedTeacherEmail = document.querySelector('input[name="selectedTeacher"]:checked').value;
        console.log('Selected Teacher:', selectedTeacherEmail);
        let studentEmail = sessionStorage.getItem('email');

        let plainObject = {
            studentEmail: studentEmail,
            teacherEmail: selectedTeacherEmail
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
        console.log(data);


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