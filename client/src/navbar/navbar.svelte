<script>
    import { isAdminLoggedIn } from "../store/adminStore";
    import { isStudentLoggedIn } from "../store/studentStore";
    import { isTeacherLoggedIn } from "../store/teacherStore";



  function redirectToLoginOrRegister(id){
    
    if(id==="login"){
      if($isAdminLoggedIn){
        window.location.href = "#/admin";
      }else if($isTeacherLoggedIn){
        window.location.href = "#/teacher";
      }else if($isStudentLoggedIn){
        window.location.href = "#/student";
      }else{
        window.location.href = "#/login";
      }
      
    }else if("regidter") {
      window.location.href = "#/register";
    }
  }
    
</script>

<!-- <nav class="sticky top-0">
  <div class="h-9 w-full bg-blue-700 items-center">
    <div class="flex justify-between">
      <div class="flex justify-normal">
        <div>Profile</div>
        <div>Profile2</div>
      </div>

      <div>
        Register
      </div>
    </div>
  </div> 
</nav> -->


<nav class="bg-white dark:bg-gray-900 fixed w-full z-20 top-0 left-0 border-b border-gray-200 dark:border-gray-600">
  <div class="max-w-screen-xl flex flex-wrap items-center justify-between mx-auto p-4">
  <div class="flex md:order-2">
      <button type="button" on:click={()=>redirectToLoginOrRegister("login")} class="text-white  bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-4 py-2 text-center mr-3 md:mr-3 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
        Login
      </button> 
      <button type="button" on:click={()=>redirectToLoginOrRegister("register")} class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-4 py-2 text-center mr-3 md:mr-0 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
        Register
      </button> 
  </div>
  <div class="items-center justify-between hidden w-full md:flex md:w-auto md:order-1" id="navbar-sticky">
    <ul class="flex flex-col p-4 md:p-0 mt-4 font-medium border border-gray-100 rounded-lg bg-gray-50 md:flex-row md:space-x-8 md:mt-0 md:border-0 md:bg-white dark:bg-gray-800 md:dark:bg-gray-900 dark:border-gray-700">
      <li>
        <a href="#/" class="block py-2 pl-3 pr-4 text-white bg-blue-700 rounded md:bg-transparent md:text-blue-700 md:p-0 md:dark:text-blue-500" aria-current="page">Home</a>
      </li>
      <li>
        <a href="#/admin" class="block py-2 pl-3 pr-4 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:hover:text-blue-700 md:p-0 md:dark:hover:text-blue-500 dark:text-white dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700">Admin</a>
      </li>
      <li>
        <a href="#/teacher" class="block py-2 pl-3 pr-4 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:hover:text-blue-700 md:p-0 md:dark:hover:text-blue-500 dark:text-white dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700">Teacher</a>
      </li>
      <li>
        <a href="#/student" class="block py-2 pl-3 pr-4 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:hover:text-blue-700 md:p-0 md:dark:hover:text-blue-500 dark:text-white dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700">Student</a>
      </li>
    </ul>
  </div>
  </div>
</nav>


<style>

  

</style>