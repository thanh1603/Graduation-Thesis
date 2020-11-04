function paging(pageCurrent,totalRescord , rowInPage, numberPageShow, onViewPage) {
    var totalPage;
    if(totalRescord % rowInPage == 0) {
        totalPage = totalRescord/rowInPage;
    }else{
        totalPage = Math.ceil(totalRescord/rowInPage);
    }

    if(totalPage == 1 ) {
        return;
    }

    var min = 0, max =0;
    var caser = 0;
    if(numberPageShow/2 > pageCurrent ) {
        min = 1;
        if(numberPageShow > totalPage) {
            max = totalPage;
        }else{
            max = numberPageShow;
        }
        caser = 1;
    }else if(pageCurrent > ( totalPage - (numberPageShow/2))){
        min = totalPage  - numberPageShow + 1;
        max = totalPage;
        caser = 2;
    }else{
        min = pageCurrent - numberPageShow/2 + 1 ;
        max =  (pageCurrent + numberPageShow/2) ;
        caser = 3;
    }

    var html = "";
    if(pageCurrent >　numberPageShow/2 ){
        html += " <li class='page-item '><a class='page-link' onclick='onViewPage(" + (pageCurrent - 1) + ")'>Previous</a></li>";
        html += " <li class='page-item '><a class='page-link' onclick='onViewPage(" + ( 1) + ")'><<</a></li>";
    }

    for(var i = min;i<= max;i++ ) {
        if(i > 0) {
            if(i == pageCurrent) {
                html += " <li class='page-item active'><a class='page-link' onclick='onViewPage(" + i + ")'>" + i + "</a></li>";
            }else{
                html += " <li class='page-item '><a class='page-link' onclick='onViewPage(" + i + ")'>" + i + "</a></li>";
            }
        }
    }
    if(max < totalPage) {
        html += " <li class='page-item '><a class='page-link' onclick='onViewPage(" + ( totalPage) + ")'>>></a></li>";
        html += " <li class='page-item '><a class='page-link' onclick='onViewPage(" + (pageCurrent + 1) + ")'>Next</a></li>";
    }

    document.getElementById("page").innerHTML = html;

    return {
        min: min,
        max:max,
        caser: caser
    }
}