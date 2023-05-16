<script>
    import { isAdminLoggedIn } from "../store/adminStore";
    import { isStudentLoggedIn } from "../store/studentStore";
    import { isTeacherLoggedIn } from "../store/teacherStore";
    
    async function onSubmit(e){
        const formData = new FormData(e.target);
        const plainObject = {};
        for (const [key, value] of formData) {
            plainObject[key] = value;
        }
        console.log(plainObject);

        const jwtToken = sessionStorage.getItem('jwtToken');
        let url = 'http://localhost:8080/resetPassword?email='+plainObject["email"]+"&"+"newPass="+plainObject["newPass"]+"&"+"oldPass="+plainObject["oldPass"];
        let response = await fetch(url, {
            method: 'PATCH',
            headers: {
                'Authorization': `Bearer ${jwtToken}`,
            },
        });

        const data = await response.json();

        if(data["success"]){
            alert("Successfully Password Changed")
            window.location.href = "#/login";
            isAdminLoggedIn.set(false);
            isStudentLoggedIn.set(false);
            isTeacherLoggedIn.set(false);
            showResetComponent.set(false);
        }else{
            alert("Password didn't changed")
        }
    }
</script>


<div class="p-6 space-y-4 md:space-y-6 sm:p-8">
    <h1 class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white">
        Reset Yout Password
    </h1>
    <form class="space-y-4 md:space-y-6" on:submit|preventDefault={onSubmit}>
        <div>
            <label for="email" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Your email</label>
            <input type="email" name="email" id="email" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" placeholder="name@company.com" required="">
        </div>
        <div>
            <label for="password" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Old Password</label>
            <input type="password" name="oldPass" id="oldPass" placeholder="••••••••" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" required="">
        </div>
        <div>
            <label for="password" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">New Password</label>
            <input type="password" name="newPass" id="newPass" placeholder="••••••••" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" required="">
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
            <!-- <a href="#" class="text-sm font-medium text-primary-600 hover:underline dark:text-primary-500">Forgot password?</a> -->
        </div>
        <button type="submit" class="w-full text-white bg-primary-600 hover:bg-primary-700 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-primary-600 dark:hover:bg-primary-700 dark:focus:ring-primary-800">Reset Password</button>
        <p class="text-sm font-light text-gray-500 dark:text-gray-400">
            Don’t have an account yet? <a href="#/register" class="font-medium text-primary-600 hover:underline dark:text-primary-500">Sign Up</a>
        </p>
    </form>
</div>