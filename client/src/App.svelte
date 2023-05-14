<script>
	import Navbar from './navbar/navbar.svelte'
  import Router from 'svelte-spa-router';
  import routes from './rout.js';
</script>

<main>
	<Navbar></Navbar>
  <Router {routes} />
</main>

<style lang="postcss" global>
  @tailwind base;
  @tailwind components;
  @tailwind utilities;
</style>
