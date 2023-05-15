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



function storeTeacherData(){
    
    const { subscribe, set, update } = writable("");

    return {
        subscribe,
        set: (val) => {set(val);},

        print: (msg) => {console.log(msg)},

        fetchAllTeacher: async ()=>{
            // debugger;
            let jwtToken;
            let email, pass;
            loggedInUser.subscribe(value => {email = value});
            loggedInUserPass.subscribe(value => {pass = value});
            let plainObject = {
                email: email,
                password: pass
            };
            let jsonData = JSON.stringify(plainObject);

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

function storeStudentData(){
    
    const { subscribe, set, update } = writable("");

    return {
        subscribe,
        set: (val) => {set(val);},

        print: (msg) => {console.log(msg)},

        fetchAllStudents: async ()=>{
            // debugger;
            let jwtToken;
            let email, pass;
            loggedInUser.subscribe(value => {email = value});
            loggedInUserPass.subscribe(value => {pass = value});
            let plainObject = {
                email: email,
                password: pass
            };
            let jsonData = JSON.stringify(plainObject);

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

export const teacherData      = storeTeacherData();
export const studentData      = storeStudentData();
export const adminData        = storeAdminData();
export let isLoggedIn         = writable(false);
export const loggedInUser     = writable("");
export const loggedInUserPass = writable("");