document.getElementById("open_menu").addEventListener("click", openCloseMenu, false)

if (document.documentElement.clientWidth < 1000)
{
    var menu = document.getElementById("menu_list");
    var search = document.getElementById("search_form");
    menu.className = "nav_hide";
    search.className = "search_hide";
}

function openCloseMenu() {
    var menu = document.getElementById("menu_list");
    var search = document.getElementById("search");
    if (menu.className != "nav") {
        menu.className = "nav";
        search.className = "search";
    }
    else {
        menu.className = "nav_hide";
        search.className = "search_hide";
    }
}