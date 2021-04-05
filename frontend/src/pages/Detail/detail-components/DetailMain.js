import { React, useEffect } from 'react';
import styles from '../index.module.css';
import Typography from '@material-ui/core/Typography';

export default function DetailMain({ propsGameInfo }) {
  useEffect(() => {
    console.log(propsGameInfo);
  });
  return (
    <div className={styles.detail_main_container}>
      <div style={{ paddingLeft: '110px' }}>
        <Typography className={styles.detail_main_default_title}>
          {propsGameInfo.appName}
        </Typography>
      </div>
      <div className={styles.detail_main_root}>
        {propsGameInfo.videoUrl === null ? (
          ''
        ) : (
          <div className={styles.detail_main_item}>
            <div style={{ padding: '10px 0' }}>
              <Typography className={styles.detail_main_title}>Intro Video</Typography>
            </div>
            <div className={styles.detail_main_video}>
              <video
                autoPlay={true}
                muted={true}
                loop={true}
                controls={false}
                className={styles.detail_main_video}
              >
                <source src={propsGameInfo.videoUrl} type="video/mp4" />
              </video>
            </div>
          </div>
        )}
        {propsGameInfo.videoUrl === null ? (
          ''
        ) : (
          <>
            <div className={styles.detail_main_item}>
              <div style={{ padding: '10px 0' }}>
                <Typography className={styles.detail_main_title}>About</Typography>
                <Typography className={styles.detail_main_title_plus}>
                  in {propsGameInfo.appName}
                </Typography>
              </div>
              <div className={styles.detail_main_video}>
                <video
                  autoPlay={true}
                  muted={true}
                  loop={true}
                  controls={false}
                  className={styles.detail_main_video}
                >
                  <source src={propsGameInfo.videoUrl} type="video/mp4" />
                </video>
              </div>
            </div>
          </>
        )}
      </div>
    </div>
  );
}
