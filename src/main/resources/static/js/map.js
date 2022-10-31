
class MarketMap {
    constructor(container, obj) {
        this.option = {
            center: new kakao.maps.LatLng(obj.center[0], obj.center[1]),
            level: obj.level
        }
        
        this.map = new kakao.maps.Map(container, this.option)
        
        this.polygonPath = []
        for (const polygonElement of obj.polygon) {
            this.polygonPath.push(new kakao.maps.LatLng(polygonElement[0], polygonElement[1]),)
        }

        let polygon = new kakao.maps.Polygon({
            path:this.polygonPath, // 그려질 다각형의 좌표 배열입니다
            strokeWeight: 2, // 선의 두께입니다
            strokeColor: '#39DE2A', // 선의 색깔입니다
            strokeOpacity: 0.8, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
            strokeStyle: 'solid', // 선의 스타일입니다
            fillColor: '#A2FF99', // 채우기 색깔입니다
            fillOpacity: 0.4 // 채우기 불투명도 입니다
        });
        polygon.setMap(this.map);
    }
}

class MarketMapBuilder {

    /**
     * 
     * @param container 지도를 표시할 div
     * @param option 카카오 지도 옵션
     */
    constructor(container, option) {
        this.option = option;
        this.mode = 'drawPolygon'
        this.prevPolygon = null
        this.polygonPath = []
        this.map = new kakao.maps.Map(container, option)
        kakao.maps.event.addListener(this.map, 'click', (event) => {
            if (this.mode === 'drawPolygon') {
                let latlng = event.latLng;
                this.polygonPath.push(latlng)
                this.refreshPolygon()
            }
        })
    }
    
    refreshPolygon() {
        this.removePolygon()
        console.log(this.polygonPath)
        let polygon = new kakao.maps.Polygon({
            path:this.polygonPath, // 그려질 다각형의 좌표 배열입니다
            strokeWeight: 2, // 선의 두께입니다
            strokeColor: '#39DE2A', // 선의 색깔입니다
            strokeOpacity: 0.8, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
            strokeStyle: 'solid', // 선의 스타일입니다
            fillColor: '#A2FF99', // 채우기 색깔입니다
            fillOpacity: 0.4 // 채우기 불투명도 입니다
        });
        this.prevPolygon = polygon
        polygon.setMap(this.map);
    }
    
    removePolygon() {
        if(this.prevPolygon != null) {
            this.prevPolygon.setMap(null)
        }
    }
    
    getObj(title) {
        let polys = []
        for (const polygonPathElement of this.polygonPath) {
            polys.push([polygonPathElement.getLat(), polygonPathElement.getLng()])
        }
        return {
            level: this.option.level,
            title: title,
            center: [this.option.center.getLat(), this.option.center.getLng()],
            polygon: polys
        }
    }
}


/*
* 시장 영역
* 주차장 위치
* 정류장 위치
* 
* */
