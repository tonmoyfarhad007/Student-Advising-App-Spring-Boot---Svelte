import Login from './login/login.svelte';
import Admin from './admin/admin.svelte';
// import Landing from './landing/landingPage.svelte';
import Register from './register/register.svelte';
import Student from './student/student.svelte';
// import Contact from './contact/contact.svelte';



export default {
	// ---------------- Landing
	// '/': Landing,
    '/login': Login,
    '/register':Register,
    '/admin': Admin,
    '/student': Student
	
}