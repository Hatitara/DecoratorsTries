'''
Tests.
'''
import random
import time
import functools

def retry(max_retries=3, delay=1):
    """
    A decorator that retries a function up to `max_retries`
    times with a `delay` (in seconds) between attempts.
    """
    def decorator(func):
        @functools.wraps(func)
        def wrapper(*args, **kwargs):
            '''
            Wrapper func.
            '''
            attempt = 0
            while attempt < max_retries:
                try:
                    return func(*args, **kwargs)
                except Exception as e:
                    attempt += 1
                    if attempt == max_retries:
                        print(f"Failed after {max_retries} attempts")
                        raise e
                    print(f"Attempt {attempt} failed; retrying in {delay} seconds...")
                    time.sleep(delay)
        return wrapper
    return decorator


@retry(max_retries=10, delay=1)
def unreliable_function():
    '''
    Unreliable function which can fail.
    '''
    if random.random() < 0.8:  #chance of failure
        print("Function failed!")
        raise ValueError("Random failure occurred!")
    print("Function succeeded!")
    return "Success!"

print(unreliable_function())
