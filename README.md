<details>
  <summary>Table of Contents</summary>
  <ol>
    <li><a href="#about-the-project">About The Project</a></li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#installation">Installation</a></li>
        <li><a href="#usage">Usage</a></li>
      </ul>
    </li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

This is a complete util library to use with Java. It provides useful classes for a bunch of things
Why SHOULD you use this API:
* It is fast and efficient
* It's easly understandable and usable for anyone familliar with Java
* Disgned for maximum extensibility and flexibility
* This project is currently actively updated

<p align="right">(<a href="#top">back to top</a>)</p>


<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these simple example steps.

### Installation

_You don't need anything more than the native Java JDK to get started!_

Clone the repo
   ```sh
   git clone https://github.com/Radi3nt/JavaUtil.git
   ```

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- USAGE EXAMPLES -->
## Usage

ArgsHelper usage exemple:

```Java
  MapArgumentRepository argumentRepository = new MapArgumentRepository("-");
  StringArgumentParser usernameParser = new StringArgumentParser("-");
  BooleanArgumentParser multiplayerParser = new BooleanArgumentParser("-");
  
  argumentRepository.addArgumentParser("username", usernameParser);
  argumentRepository.addArgumentParser("multiplayer", multiplayerParser);

  ArgParser argParser = new ArgParser(new AlwaysValidScheme(), argumentRepository, new ListParserResultBuilderFactory());

  try {
      argParser.construct(args);
  } catch (ArgumentException e) {
      e.printStackTrace();
      System.exit(-1);
  }

  String username = usernameParser.hasValue() ? usernameParser.getStringArgument().getValue() : "Radi3nt";
```

This code will parse arguments if they are present, and return a String value of "username", and a boolean value of "multiplayer". Launching this with all arguments would look like this:

```ssh
java -jar App.jar -username "What a wonderfull username" -multiplayer false
```

_More exmemples will be added in the future_

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- ROADMAP -->
## Roadmap

- [ ] Cleaning up LogUtil
- [ ] Adding more helpers

See the [open issues](https://github.com/Radi3nt/JavaUtil/issues) for a full list of proposed features (and known issues).

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch
3. Commit your Changes
4. Push to the Branch
5. Open a Pull Request

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- LICENSE -->
## License

Distributed under the Apache License 2.0. See `LICENSE.txt` for more information.

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- CONTACT -->
## Contact

Radi3nt - pro.radi3nt@gmail.com

Project Link: [https://github.com/Radi3nt/JavaUtil](https://github.com/Radi3nt/JavaUtil)

<p align="right">(<a href="#top">back to top</a>)</p>
