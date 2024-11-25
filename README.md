## Inspiration

Now slightly over 50 years old, George Mason University is a very young institution. Nevertheless, it attracts thousands of students yearly, and it has become the largest public university in Virginia by enrollment. However, despite this growth, the development of a unique identity and culture has lagged behind. Think of every fencing match on the plaza, velociraptor club flyer, and the many campus events that pass by and may never happen again. Generations of Mason students may never know what the crazy experiences that happened before them. 

As freshmen, we lived in Dickenson Hall, and one day we discovered that our floor, Dickenson Hall First Floor, had its own location on google separate from the building. There, the residents from the previous year posted images like posing with their Danny DeVito statue and "Dogs Playing Poker" paintings around the floor. Back then, we were amazed to see the amount of fun we could have. We sought to capture this magic with our application, Mason Speaks. By allowing students to post their experiences around campus, we hope that Mason's students can leave their imprint on the land for future cohorts of students to learn from and enjoy. With this app, Mason's community can post, remember, and pass on their combined stories and legacies. 

## What it does

Mason Speaks is an application that gives people the ability to share their experiences for others to see. Each story is tagged with GPS coordinates to tie it to the land. As people explore the GMU campus, they will be shown a limited list of some of the nearby stories that people have uploaded. This incentivizes people to explore the campus to find new stories so that they can learn more about the lore of Mason. Furthermore, even if they explore a location multiple times, they uncover new stories each time. When a user wants to share their experiences, they can enter a title and a description of their experience along with an image all on the app. Almost like a perpetual orientation, new students would find this invaluable for getting to know their community. Upperclassmen could also use this application to make the most of their university experience by encouraging them to share and remember the parts of their time at Mason that they enjoy.

## How we built it

We developed Mason Speaks by combining numerous web and UI/UX technologies.  The heart of this hack is our Android application. We used Kotlin and Retrofit to create an attractive Mason-themed interface with access to our smartphone's camera and GPS information. We then used a Python-based FastAPI server to handle user data transactions between our Android application and database. Finally, our database was built upon MongoDB which enabled advanced object-oriented data storage with geospatial awareness for two dimensional spherical locations. Our backend infrastructure is hosted live on an AWS cloud instance.

## Challenges we ran into

We ran into numerous challenges related to Android app development and the handling of geospatial coordinates. Determining the stories closest to a user was difficult due to the spherical geometry of Earth. Since longitude and latitude coordinates cannot map to a two-dimensional space, it was not possible to use simple geometric properties like the Theorem of Pythagoras. Instead, we had to rebuild our database schema in order to leverage the location-aware algorithms in MongoDB for sorting by distance on a sphere.

However, the greatest challenge we faced was achieving file uploads for users to submit their images. It took our leading Android Developer over 12 hours of the competition to figure out a solution this problem. Eventually the code was written to utilize Android's close connection with Google and Google Services to connect with a device's browser and upload images using a webpage and still including a seamless and simple uploading process.

## Accomplishments that we're proud of

We are proud of the entire application as a whole. We were able to create a working application that users would be able to interact with as is. With a functional frontend and backend, we were able to demonstrate the operation and utility of this application. We overcame significant difficulties and faced roadblocks that took over 12 hours to solve. The skills we have learned from this will be immeasurably helpful for our future. 

## What we learned

Throughout this project, we learned so much about data handling practices and optimizing user experience. We implemented industry standard protocols for storing location data, performing server-client interactions, and analyzing non-cartesian coordinates. We also set out to create a very simple and streamlined user experience. We were able to figure out how to do this in Android Kotlin.

## What's next for Mason Speaks

The sky is the limit for Mason Speaks, but it has to start with users. After adding a user account creation system, we would be able to begin deploying this application and allowing people to start sharing their stories.
