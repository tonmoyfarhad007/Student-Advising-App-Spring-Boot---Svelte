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

  let col = ["home-col","admin-col","teacher-col", "student-col"]
  function selectCol(id){
    col.forEach(e => {
      if(e==id){
        document.getElementById(e).style.color = "#0000FF";
      }else{
        document.getElementById(e).style.color = "#000000";
      }
      
    });

  }
    
</script>



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
        <a href="#/" on:click={()=>selectCol("home-col")} id="home-col" class="block py-2 pl-3 pr-4 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:hover:text-blue-700 md:p-0 md:dark:hover:text-blue-500 dark:text-white dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700" >Home</a>
      </li>
      <li>
        <a href="#/admin" on:click={()=>selectCol("admin-col")} id="admin-col" class="block py-2 pl-3 pr-4 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:hover:text-blue-700 md:p-0 md:dark:hover:text-blue-500 dark:text-white dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700">Admin</a>
      </li>
      <li>
        <a href="#/teacher" on:click={()=>selectCol("teacher-col")} id="teacher-col" class="block py-2 pl-3 pr-4 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:hover:text-blue-700 md:p-0 md:dark:hover:text-blue-500 dark:text-white dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700">Teacher</a>
      </li>
      <li>
        <a href="#/student" on:click={()=>selectCol("student-col")} id="student-col" class="block py-2 pl-3 pr-4 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:hover:text-blue-700 md:p-0 md:dark:hover:text-blue-500 dark:text-white dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700">Student</a>
      </li>
    </ul>
  </div>
  </div>
</nav>


<style>

  

</style>