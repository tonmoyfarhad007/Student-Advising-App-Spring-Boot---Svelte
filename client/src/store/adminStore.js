import { writable } from 'svelte/store';

function storeAdminData(){
    
    const { subscribe, set, update } = writable("");

    return {
        subscribe,
        set: (val) => {set(val);},

        print: (msg) => {console.log(msg)},

        store:  (data)=>{
            console.log(data);
            set(data);
        },

        fetchAdminData: async ()=>{
            
        }

            
        
    }
}


function getDataFromSession(){
    
    const { subscribe, set, update } = writable("");

    return {
        subscribe,
        set: (val) => {set(val);},

        print: (msg) => {console.log(msg)},

        setSessionDatatoStore: ()=>{
            let name = sessionStorage.getItem('name');
            let email = sessionStorage.getItem('email');
            let phoneNo = sessionStorage.getItem('phoneNo');

            let data = {
                name: name,
                email: email,
                phoneNo: phoneNo
            }
            adminData.store(data);

        }

        

            
        
    }
}




function storeTeacherData(){
    
    const { subscribe, set, update } = writable("");

    return {
        subscribe,
        set: (val) => {set(val);},

        print: (msg) => {console.log(msg)},

        fetchAllTeacher: async ()=>{
            const jwtToken = sessionStorage.getItem('jwtToken');

            let response = await fetch("http://localhost:8080/getAllTeachers", {
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${jwtToken}`
                } 
            });

            const data = await response.json();
            console.log(data);
            teacherData.set(data);

            
        }

            
        
    }
}

function avtiveDeactive(){
    
    const { subscribe, set, update } = writable("");

    return {
        subscribe,
        set: (val) => {set(val);},

        print: (msg) => {console.log(msg)},

        deactiveUser: async (email)=>{
            const jwtToken = sessionStorage.getItem('jwtToken');
            let url = "http://localhost:8080/deactiveAccount?email="+email;
            let response = await fetch(url, {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${jwtToken}`
                } 
            });

            const data = await response.json();
            console.log(data);
            // teacherData.set(data); activateAccount

            
        },
        activateUser: async (email)=>{
            const jwtToken = sessionStorage.getItem('jwtToken');
            let url = "http://localhost:8080/activateAccount?email="+email;
            let response = await fetch(url, {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${jwtToken}`
                } 
            });

            const data = await response.json();
            console.log(data);
            // teacherData.set(data); activateAccount

            
        }        
    }
}



function storeStudentData(){
    
    const { subscribe, set, update } = writable("");

    return {
        subscribe,
        set: (val) => {set(val);},

        print: (msg) => {console.log(msg)},

        fetchAllStudents: async ()=>{
            debugger;
            const jwtToken = sessionStorage.getItem('jwtToken');
            let response = await fetch("http://localhost:8080/getAllStudents", {
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${jwtToken}`
                } 
            });

            const data = await response.json();
            console.log(data);
            studentData.set(data);
    
        }

            
        
    }
}

export const activity         = avtiveDeactive();
export const teacherData      = storeTeacherData();
export const dataFromSession  = getDataFromSession();
export const studentData      = storeStudentData();
export const adminData        = storeAdminData();
export let   isAdminLoggedIn  = writable(false);
export const loggedInUser     = writable("");
export const loggedInUserPass = writable("");