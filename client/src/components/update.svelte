<script>
    import {studentProfileData,showUpdateComponent} from '../store/studentStore.js';
    import { singleTeacherData } from '../store/teacherStore.js';

    export let name;
    export let email;
    export let phoneNo;
    export let departmentName;
    export let userType;

    async function onSubmit(e){

        const formData = new FormData(e.target);
        const plainObject = {};
        for (const [key, value] of formData) {
            plainObject[key] = value;
        }
        plainObject["userType"] = userType;
        plainObject["email"]    = email;
        const jsonData = JSON.stringify(plainObject);
        console.log(jsonData);
        const jwtToken = sessionStorage.getItem('jwtToken');

        let response = await fetch('http://localhost:8080/update', {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${jwtToken}`,
            },
            body: jsonData
        });

        const data = await response.json();
        if(userType ==="Student"){
            studentProfileData.setStudentData(data);
        }else if(userType ==="Teacher"){
            singleTeacherData.setSingleTeacherData(data);
        }
        if(data!=="cannot update"){
            showUpdateComponent.set(false);
            alert("updated successfully!!")
        }
        
        

    }

</script>

<div class="p-6 space-y-4 md:space-y-4 sm:p-8">
<h1 class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white">
    Update Your Profile
</h1>

<form class="space-y-2 md:space-y-1" on:submit|preventDefault={onSubmit}>
    <div>
        <label for="full-name" class="block mb-1 text-sm font-medium text-gray-900 dark:text-white">Full Name</label>
        <input type="text" name="name" id="name" bind:value={name} placeholder="Jeson Bucky" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" required="">
    </div>
    <div>
        <label for="phone-no" class="block mb-1 text-sm font-medium text-gray-900 dark:text-white">Phone No.</label>
        <input type="text" name="phoneNo" id="phoneNo" bind:value={phoneNo} placeholder="+8801xxxxxxxx" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" required="">
    </div>
    <div>
        <label for="department-name" class="block mb-1 text-sm font-medium text-gray-900 dark:text-white">Department Name</label>
        <input type="text" name="departmentName" id="departmentName" bind:value={departmentName} placeholder="Department name" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" required="">
    </div>  
    
    <button type="submit" class="w-full text-white bg-primary-600 hover:bg-primary-700 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-primary-600 dark:hover:bg-primary-700 dark:focus:ring-primary-800">Update Profile</button>
</form>
</div>