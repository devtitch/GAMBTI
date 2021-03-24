import styles from './GenreList.module.css';
import React, { useState, useEffect } from 'react';
import GenreCard from 'src/components/GenreCard/GenreCard';
import Container from '@material-ui/core/Container';
import { useHistory, generatePath } from 'react-router-dom';
import routerInfo from 'src/constants/routerInfo';

export default function GenreList({ propsOrder }) {
  const [genres, setGenres] = useState(new Array());
  const history = useHistory();

  useEffect(() => {
    fetch('jsonFiles/genres.json')
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        setGenres(data.genres);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  return (
    <div>
      <Container maxWidth="lg" style={{ backgroundColor: '#2a2b2c', paddingTop: '10px' }}>
        <h2 style={{ color: 'white', margin: '0px' }}>Type of Games</h2>
      </Container>

      <Container maxWidth="lg" className={styles.genre_list}>
        {genres.map((genre, i) => (
          <div key={i} className={styles.genre_item}>
            <GenreCard
              imagePath={genre.path}
              genreName={genre.name}
              onClickFunc={() => {
                history.push(
                  generatePath(routerInfo.PAGE_URLS.GAMES, {
                    order: propsOrder,
                    genre: genre.name,
                  })
                );
              }}
            ></GenreCard>
          </div>
        ))}
      </Container>
    </div>
  );
}