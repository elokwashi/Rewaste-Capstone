# C23-PS388 [Rewaste]
<br>
<p align="center">
  <img src="https://raw.githubusercontent.com/elokwashi/Rewaste-Capstone/7acbcb4958246015948b1abaa26c3357aebfbf5b/ReWaste.png" >
</p>

## About The Project
Waste Classifier Application that can be implemented to android devices  to classify which category does any waste belongs to as soon as it gets detected by the camera from the device. We also provide articles on how to process or manage recyclable, non-recyclable, or organic wastes. Hopefully with this technology, wastes could be more easily organized and reduce lots of recyclable waste being burned which can create pollution.

## Team Members

### Team ID : C23-PS388

| Name                           | Bangkit ID  | Learning Path      | University                          | Contact                                                                                                                                                                                       |
| ------------------------------ | ----------- | ------------------ |  ---------------------------------- |-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Yogiswara                      | A185DSX3158 | Mobile Development | Universitas Islam Indonesia         | <a href="https://www.linkedin.com/in/yogi-swara/"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" /></a>                            |
| Muhammad Sayyidin Hawibowo     | A185DSX3097 | Mobile Development | Universitas Islam Indonesia         | <a href="https://www.linkedin.com/in/msayyidinh/"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" /></a>                            |
| Muhammad Dimas Haryo S         | C185DSX4920 | Cloud Computing    | Universitas Islam Indonesia         | <a href="https://www.linkedin.com/in/muhammaddimasharyo/"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" /></a>                            |
| Mohamad Adhikasurya Haidar     | M169DSX0557 | Machine Learning   | Universitas Gadjah Mada             | <a href="https://www.linkedin.com/in/adhikasurya/"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" /></a>                            |
| Elok Washilatul Fadhilah A.S.  | M169DSY0186 | Machine Learning   | Universitas Gadjah Mada             | <a href="https://www.linkedin.com/in/elok-washilatul-fadhilah-a-s-469370154/"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" /></a>                            |

## Documentation
## Mobile Development

<p align="center">
	<img src="https://github.com/elokwashi/Rewaste-Capstone/blob/main/Image/Onboarding.png" width="25%"> &nbsp; &nbsp; &nbsp;
	<img src="https://github.com/elokwashi/Rewaste-Capstone/blob/main/Image/Home.png" width="25%"> &nbsp; &nbsp; &nbsp;
	<img src="https://github.com/elokwashi/Rewaste-Capstone/blob/main/Image/Classification.png" width="25%">
</p>

We using the [Kotlin programming language](https://kotlinlang.org/) to develop applications, also using [Retrofit](https://square.github.io/retrofit/) to handle all data connection from android to the API. Using [Tensor Flow Lite](https://www.tensorflow.org/lite) to implement machine learning model to the application. You can check out the Rewaste app [demo](https://drive.google.com/file/d/1sycjOoHekKJMQpy1-4JUkL6wCVK3z4-2/view?usp=sharing). Here is the link to download the [APK](https://drive.google.com/file/d/1cgoo9bAqS7onkz1kUhacV_gpUZQNGYoD/view?usp=sharing) file.
### Step to Preview and Open the Project
1. Download the project zip from Mobile Development file. We use git LFS because app file was more than 100mb.
2. Extract it.
3. Open the folder in your Android Studio.
### UI/UX Design
We using Figma to Design User Interface and User Experience, here is the  [design](https://www.figma.com/file/fcVDZYTG1qBFnEQudKMSxl/Rewaste?type=design&node-id=4%3A3&t=mfF54O2BwmQ6xD5q-1)
### Feature 
1. Add and Show Articles.
2. Image (Waste) Classification.
3. Search.
4. Profile(Privacy and Settings).
5. Localization.
## Machine Learning

## Cloud Computing
API built with [Node.js](https://nodejs.org/en), [Express.js](http://expressjs.com/), [Google Cloud Run (GCP)](https://cloud.google.com/run), and [MongoDB](https://www.mongodb.com/). This project showcases an efficient solution for creating high-performance APIs with seamless integration of these advanced technologies.

### Prerequisites
- Node.js and npm (Node Package Manager) installed on your machine.
- A Google Cloud Platform (GCP) account with Cloud Run service enabled.
- MongoDB set up and running.

### API Usage
The API offers a range of endpoints for seamless interaction with MongoDB as a database. Some of the endpoints include:
- GET /api/v1: Retrieves a specific resource.
- POST /api/v1: Creates a new resource.
- PUT /api/v1/:id: Updates an existing resource identified by the provided ID.
- DELETE /api/v1/:id: Deletes the resource identified by the provided ID.

### API Documentation
For API documentation, see the following link [Restful APIs ReWaste](https://github.com/elokwashi/Rewaste-Capstone/blob/main/Cloud%20Computing/README.md)
