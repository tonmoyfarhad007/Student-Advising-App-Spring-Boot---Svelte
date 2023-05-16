<script>

    import {adminData, isAdminLoggedIn, loggedInUserPass, loggedInUser} from '../store/adminStore.js';
    import { studentProfileData, isStudentLoggedIn } from '../store/studentStore.js';
    import { isTeacherLoggedIn,singleTeacherData } from '../store/teacherStore.js';

    async function onSubmit(e) {
        const formData = new FormData(e.target);
        const plainObject = {};
        let jwtToken;
        for (const [key, value] of formData) {
            plainObject[key] = value;
        }
        loggedInUser.set(plainObject["email"]);
        loggedInUserPass.set(plainObject["password"]);

        // localStorage.setItem('email', plainObject["email"]);

        const jsonData = JSON.stringify(plainObject);
        
        let jwtResponse = await fetch('http://localhost:8080/authenticate', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer '
            },
            body: jsonData
        });
        const jwtTokenData = await jwtResponse.json();
        jwtToken = jwtTokenData['token'];
        sessionStorage.setItem("jwtToken",jwtToken);


        let response = await fetch('http://localhost:8080/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${jwtToken}`,
            },
            body: jsonData
        });

        const data = await response.json();
        console.log("login Data---->",data);
        if(data["userDetails"]!=="Not authorised"){
            sessionStorage.setItem('userType', data["userType"]);
            if(data['userType']=="Admin") {
                isAdminLoggedIn.set(true);
                isStudentLoggedIn.set(false);
                isTeacherLoggedIn.set(false);
                sessionStorage.setItem('name', data["userDetails"].name);
                sessionStorage.setItem('email', data["userDetails"].email);
                sessionStorage.setItem('phoneNo', data["userDetails"].phoneNo);
                adminData.store(data['userDetails']);
                window.location.href = "#/admin";
            }else if(data['userType']=="Student"){
                isStudentLoggedIn.set(true);
                isAdminLoggedIn.set(false);
                isTeacherLoggedIn.set(false);
                sessionStorage.setItem('name', data["userDetails"].name);
                sessionStorage.setItem('email', data["userDetails"].email);
                sessionStorage.setItem('phoneNo', data["userDetails"].phoneNo);
                sessionStorage.setItem('studentId', data["userDetails"].studentId);
                sessionStorage.setItem('active', data["userDetails"].active);
                sessionStorage.setItem('departmentName', data["userDetails"].departmentName);
                studentProfileData.setStudentData(data['userDetails']);
                window.location.href = "#/student";
            }else if(data['userType']=="Teacher"){
                isTeacherLoggedIn.set(true);
                isStudentLoggedIn.set(false);
                isAdminLoggedIn.set(false);
                sessionStorage.setItem('name', data["userDetails"].name);
                sessionStorage.setItem('email', data["userDetails"].email);
                sessionStorage.setItem('phoneNo', data["userDetails"].phoneNo);
                sessionStorage.setItem('teacherId', data["userDetails"].teacherId);
                sessionStorage.setItem('active', data["userDetails"].active);
                sessionStorage.setItem('departmentName', data["userDetails"].departmentName);
                singleTeacherData.setSingleTeacherData(data['userDetails']);
                window.location.href = "#/teacher";
            }else{
                alert("Somthing wrong with registration please try again");
            }

        }else{
            alert(data["userDetails"]);
        }
        
        
        
    }

</script>


<section class="bg-gray-50 dark:bg-gray-900">
    <div class="flex flex-col items-center justify-center px-6 py-8 mx-auto md:h-screen lg:py-0">
        
        <div class="w-full bg-white rounded-lg shadow dark:border md:mt-0 sm:max-w-md xl:p-0 dark:bg-gray-800 dark:border-gray-700">
            <div class="p-6 space-y-4 md:space-y-6 sm:p-8">
                <h1 class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white">
                    Sign in to your account
                </h1>
                <form class="space-y-4 md:space-y-6" on:submit|preventDefault={onSubmit}>
                    <div>
                        <label for="email" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Your email</label>
                        <input type="email" name="email" id="email" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" placeholder="name@company.com" required="">
                    </div>
                    <div>
                        <label for="password" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Password</label>
                        <input type="password" name="password" id="password" placeholder="••••••••" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" required="">
                    </div>
                    <div class="flex items-center justify-between">
                        <div class="flex items-start">
                            <div class="flex items-center h-5">
                              <input id="remember" aria-describedby="remember" type="checkbox" class="w-4 h-4 border border-gray-300 rounded bg-gray-50 focus:ring-3 focus:ring-primary-300 dark:bg-gray-700 dark:border-gray-600 dark:focus:ring-primary-600 dark:ring-offset-gray-800" required="">
                            </div>
                            <div class="ml-3 text-sm">
                              <label for="remember" class="text-gray-500 dark:text-gray-300">Remember me</label>
                            </div>
                        </div>
                        <a href="#" class="text-sm font-medium text-primary-600 hover:underline dark:text-primary-500">Forgot password?</a>
                    </div>
                    <button type="submit" class="w-full text-white bg-primary-600 hover:bg-primary-700 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-primary-600 dark:hover:bg-primary-700 dark:focus:ring-primary-800">Sign in</button>
                    <p class="text-sm font-light text-gray-500 dark:text-gray-400">
                        Don’t have an account yet? <a href="#/register" class="font-medium text-primary-600 hover:underline dark:text-primary-500">Sign up</a>
                    </p>
                </form>
            </div>
        </div>
    </div>
  </section>