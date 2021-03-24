from bs4 import BeautifulSoup as bs
from urllib.request import urlopen
import pymysql          #mariaDB 사용
import sqlalchemy      #DB 접속 엔진
import json
import pandas as pd

# 일단 game의 id와 url을 가져온다
# url을 하나씩 순회하면서 bs4를 이용하여 대표 이미지 주소 가져오기
# <img class = "game_header_image_full" />
with open('config.json', 'r') as f:
    config = json.load(f)
HOST = config['DATABASE']['HOST']
USER = config['DATABASE']['USER']
PW = config['DATABASE']['PW']

DATABASE_URL = 'mysql+pymysql://'+USER+':'+PW+'@'+HOST+':3306/gambti?charset=utf8mb4'
engine_mariadb = sqlalchemy.create_engine(DATABASE_URL, echo=False)

def get_data():
    # mariadb의 table 값을 읽어서 DataFrame으로 반환
    game_data = pd.read_sql_table('game', engine_mariadb, columns={'game_id', 'url'})

    return game_data

def save_mariadb(data):
    #TODO : 이걸로 하면 insert into여서 update를 할 수 있는 것을 찾아야함
    data.to_sql(name='game', con=engine_mariadb, index=False, if_exists='append') 

def image_scrapy(data):
    #url, game_id DataFrame을 순회
    rows_list = []
    for i in range(len(data)):
        row = data.iloc[i, :]
        url = row['url']
        game_id = row['game_id']
        
        with urlopen(url) as response:
            soup = bs(response, 'html.parser')
            for anchor in soup.select("img.game_header_image_full"):
                img_url = anchor.get('src')
                print(i)

        rows_list.append({"game_id":game_id, "logo_image_path":url})

    #TODO: json 파일로 생성하기
    #app_name은 not null이다. 
    df = pd.DataFrame(rows_list)
    
    print("스크래핑 끝!")
    save_mariadb(df)

def main():
    print("[+] 게임 데이터 가져오기")
    game_data = get_data()
    print("[*] done..")
    print("[+] url 크롤링")
    image_scrapy(game_data)
    print("[*] success")

if __name__ == "__main__":
    main()